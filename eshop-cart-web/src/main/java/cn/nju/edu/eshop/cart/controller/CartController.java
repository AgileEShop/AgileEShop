package cn.nju.edu.eshop.cart.controller;


import cn.nju.edu.eshop.annotations.LoginRequired;
import cn.nju.edu.eshop.bean.CartItem;
import cn.nju.edu.eshop.bean.Product;
import cn.nju.edu.eshop.service.CartService;
import cn.nju.edu.eshop.service.ProductService;
import cn.nju.edu.eshop.util.CookieUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {
    @Reference
    ProductService productService;

    @Reference
    CartService cartService;

    @RequestMapping("checkCart")
    @LoginRequired(loginSuccess = false)
    public String checkCart(String isChecked, String productId, HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {

        String userId = (String)request.getAttribute("userId");
        String nickname = (String)request.getAttribute("nickname");

        // 调用服务，修改状态
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setIsChecked(isChecked);
        cartService.checkCart(cartItem);

        // 将最新的数据从缓存中查出，渲染给内嵌页
        List<CartItem> cartItems = cartService.cartList(userId);
        modelMap.put("cartList",cartItems);

        // 被勾选商品的总额
        BigDecimal totalAmount =getTotalAmount(cartItems);
        modelMap.put("totalAmount",totalAmount);
        return "cartListInner";
    }

    @RequestMapping("cartList")
    @LoginRequired(loginSuccess = false)
    public String cartList(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {

        List<CartItem> cartItems = new ArrayList<>();
        String userId = (String)request.getAttribute("userId");
        String nickname = (String)request.getAttribute("nickname");

        if(StringUtils.isNotBlank(userId)){
            // 已经登录查询db
            cartItems = cartService.cartList(userId);
        }else{
            // 没有登录查询cookie
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            if(StringUtils.isNotBlank(cartListCookie)){
                cartItems = JSON.parseArray(cartListCookie,CartItem.class);
            }
        }

        for (CartItem omsCartItem : cartItems) {
            omsCartItem.setTotalPrice(omsCartItem.getPrice().multiply(omsCartItem.getQuantity()));
        }

        modelMap.put("cartList",cartItems);
        // 被勾选商品的总额
        BigDecimal totalAmount =getTotalAmount(cartItems);
        modelMap.put("totalAmount",totalAmount);
        return "cartList";
    }

    private BigDecimal getTotalAmount(List<CartItem> cartItems) {
        BigDecimal totalAmount = new BigDecimal("0");

        for (CartItem cartItem : cartItems) {
            BigDecimal totalPrice = cartItem.getTotalPrice();

            if(cartItem.getIsChecked().equals("1")){
                totalAmount = totalAmount.add(totalPrice);
            }
        }

        return totalAmount;
    }

    @RequestMapping("addToCart")
    @LoginRequired(loginSuccess = false)
    public String addToCart(String productId, int quantity, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<CartItem> cartItems = new ArrayList<>();

        // 调用商品服务查询商品信息
        Product productInfo = productService.getProductById(productId);

        // 将商品信息封装成购物车信息
        CartItem cartItem1 = new CartItem();
        cartItem1.setCreateDate(new Date());
        cartItem1.setDeleteStatus(0);
        cartItem1.setModifyDate(new Date());
        cartItem1.setPrice(productInfo.getPrice());
        cartItem1.setProductCatalog2Id(productInfo.getCatalog2Id());
        cartItem1.setProductId(productInfo.getId());
        cartItem1.setProductTitle(productInfo.getTitle());
        cartItem1.setProductPic(productInfo.getUrls());
        cartItem1.setProductId(productId);
        cartItem1.setQuantity(new BigDecimal(quantity));


        // 判断用户是否登录
        String userId = (String)request.getAttribute("userId");
        String nickname = (String)request.getAttribute("nickname");


        if (StringUtils.isBlank(userId)) {
            // 用户没有登录

            // cookie里原有的购物车数据
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            if (StringUtils.isBlank(cartListCookie)) {
                // cookie为空
                cartItems.add(cartItem1);
            } else {
                // cookie不为空
                cartItems = JSON.parseArray(cartListCookie, CartItem.class);
                // 判断添加的购物车数据在cookie中是否存在
                boolean exist = if_cart_exist(cartItems, cartItem1);
                if (exist) {
                    // 之前添加过，更新购物车添加数量
                    for (CartItem cartItem : cartItems) {
                        if (cartItem.getProductId().equals(cartItem1.getProductId())) {
                            cartItem.setQuantity(cartItem.getQuantity().add(cartItem1.getQuantity()));
                        }
                    }
                } else {
                    // 之前没有添加，新增当前的购物车
                    cartItems.add(cartItem1);
                }
            }

            // 更新cookie
            CookieUtil.setCookie(request, response, "cartListCookie", JSON.toJSONString(cartItems), 60 * 60 * 72, true);
        } else {
            // 用户已经登录
            // 从db中查出购物车数据
            CartItem cartItemFromDb = cartService.ifCartExistByUser(userId,productId);

            if(cartItemFromDb==null){
                // 该用户没有添加过当前商品
                cartItem1.setUserId(userId);
                cartItem1.setUserNickname("test小明");
                cartItem1.setQuantity(new BigDecimal(quantity));
                cartService.addCart(cartItem1);

            }else{
                // 该用户添加过当前商品
                cartItemFromDb.setQuantity(cartItemFromDb.getQuantity().add(cartItem1.getQuantity()));
                cartService.updateCart(cartItemFromDb);
            }

            // 同步缓存
            cartService.flushCartCache(userId);
        }


        return "redirect:/success.html";
    }

    private boolean if_cart_exist(List<CartItem> omsCartItems, CartItem omsCartItem) {

        boolean b = false;

        for (CartItem cartItem : omsCartItems) {
            String productId = cartItem.getProductId();

            if (productId.equals(omsCartItem.getProductId())) {
                b = true;
            }
        }


        return b;
    }

}
