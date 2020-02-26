package cn.nju.edu.eshop.service;

import cn.nju.edu.eshop.bean.CartItem;

import java.util.List;

public interface CartService {
    CartItem ifCartExistByUser(String userId, String skuId);

    void addCart(CartItem cartItem);

    void updateCart(CartItem cartItemFromDb);

    void flushCartCache(String userId);

    List<CartItem> cartList(String userId);

    void checkCart(CartItem omsCartItem);
}
