package cn.nju.edu.eshop.search.service.impl;

import cn.nju.edu.eshop.bean.Product;
import cn.nju.edu.eshop.bean.ProductSearchParam;
import cn.nju.edu.eshop.service.SearchService;
import com.alibaba.dubbo.config.annotation.Service;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    JestClient jestClient;

    @Override
    public List<Product> list(ProductSearchParam productSearchParam) {
        String dslStr = getSearchDsl(productSearchParam);
        System.err.println(dslStr);
        // 用api执行复杂查询
        List<Product> productList = new ArrayList<>();
        Search search = new Search.Builder(dslStr).addIndex("agileeshop").addType("Product").build();
        SearchResult execute = null;
        try{
            execute = jestClient.execute(search);
        }catch (IOException e){
            e.printStackTrace();
        }
        List<SearchResult.Hit<Product,Void>> hits = execute.getHits(Product.class);
        for(SearchResult.Hit<Product,Void> hit:hits){
            Product source = hit.source;
            Map<String, List<String>> highlight = hit.highlight;
            if(highlight!=null){
                String productTitle = highlight.get("productTitle").get(0);
                source.setTitle(productTitle);
            }
            productList.add(source);
        }
        System.out.println(productList.size());
        return productList;
    }

    private String getSearchDsl(ProductSearchParam productSearchParam) {
        String[] productValueList = productSearchParam.getValueId();
        String keyword = productSearchParam.getKeyword();
        String catalog2Id = productSearchParam.getCatalog2Id();
        //jest的dsl工具
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        //filter
        if(StringUtils.isNotBlank(catalog2Id)){
            TermQueryBuilder termQueryBuilder = new TermQueryBuilder("catalog2Id",catalog2Id);
            boolQueryBuilder.filter(termQueryBuilder);
        }
        if(productValueList!=null){
            for(String productValue:productValueList){
                TermQueryBuilder termQueryBuilder = new TermQueryBuilder("productValueList.valueId()",productValue);
                boolQueryBuilder.filter(termQueryBuilder);
            }
        }
        //must
        if(StringUtils.isNotBlank(keyword)){
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("productName",keyword);
            boolQueryBuilder.must(matchQueryBuilder);
        }
        //query
        searchSourceBuilder.query(boolQueryBuilder);
        //highlight
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red;'>");
        highlightBuilder.field("productName");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlight(highlightBuilder);
        // sort
        searchSourceBuilder.sort("id", SortOrder.DESC);
        // from
        searchSourceBuilder.from(0);
        // size
        searchSourceBuilder.size(20);
        // aggs
        TermsBuilder groupby_attr = AggregationBuilders.terms("groupby_attr").field("productValueList.valueId");
        searchSourceBuilder.aggregation(groupby_attr);
        return searchSourceBuilder.toString();
    }

}
