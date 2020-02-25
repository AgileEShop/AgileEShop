package cn.nju.edu.eshop.search.search.Controller;

import cn.nju.edu.eshop.annotations.LoginRequired;
import cn.nju.edu.eshop.bean.Product;
import cn.nju.edu.eshop.bean.ProductSearchParam;
import cn.nju.edu.eshop.service.SearchService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {
    @Reference
    SearchService searchService;
    @RequestMapping("list.html")
    public String list(ProductSearchParam productSearchParam, ModelMap modelMap){// 二级分类id、关键字、
        //调用搜索服务，返回搜索结果
        List<Product> productList = searchService.list(productSearchParam);
        modelMap.put("productList",productList);
        // 抽取检索结果锁包含的平台属性集合
        Set<String> valueIdSet = new HashSet<>();
        for (Product product : productList) {

        }

        String urlParam = getUrlParam(productSearchParam);
        modelMap.put("urlParam", urlParam);
        String keyword = productSearchParam.getKeyword();
        if (StringUtils.isNotBlank(keyword)) {
            modelMap.put("keyword", keyword);
        }

        return "list";
    }

    private String getUrlParamForCrumb(ProductSearchParam pmsSearchParam, String delValueId) {
        String keyword = pmsSearchParam.getKeyword();
        String catalog3Id = pmsSearchParam.getCatalog2Id();
        String[] skuAttrValueList = pmsSearchParam.getValueId();

        String urlParam = "";

        if (StringUtils.isNotBlank(keyword)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "keyword=" + keyword;
        }

        if (StringUtils.isNotBlank(catalog3Id)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "catalog3Id=" + catalog3Id;
        }

        if (skuAttrValueList != null) {
            for (String pmsSkuAttrValue : skuAttrValueList) {
                if (!pmsSkuAttrValue.equals(delValueId)) {
                    urlParam = urlParam + "&valueId=" + pmsSkuAttrValue;
                }
            }
        }

        return urlParam;
    }

    private String getUrlParam(ProductSearchParam pmsSearchParam) {
        String keyword = pmsSearchParam.getKeyword();
        String catalog3Id = pmsSearchParam.getCatalog2Id();
        String[] skuAttrValueList = pmsSearchParam.getValueId();

        String urlParam = "";

        if (StringUtils.isNotBlank(keyword)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "keyword=" + keyword;
        }

        if (StringUtils.isNotBlank(catalog3Id)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "catalog2Id=" + catalog3Id;
        }

        if (skuAttrValueList != null) {

            for (String pmsSkuAttrValue : skuAttrValueList) {
                urlParam = urlParam + "&valueId=" + pmsSkuAttrValue;
            }
        }

        return urlParam;
    }

    @RequestMapping("index")
    @LoginRequired(loginSuccess = false)
    public String index() {
        return "index";
    }

}
