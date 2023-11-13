package com.cleancode.shopping.service;

import com.cleancode.shopping.entity.CartDetails;
import com.cleancode.shopping.payload.CartDetailsResponse;

public class CartServiceImpl implements CartService{
    @Override
    public CartDetailsResponse viewCart(String userId) {
        return null;
    }

    @Override
    public String addToCart(CartDetails cartDetails) {
        return null;
    }

    @Override
    public String reduceQuantityOfAProduct(long userId, long productId) {
        return null;
    }

    @Override
    public String removeAProduct(long userId, long productId) {
        return null;
    }

    @Override
    public String removeAllFromCart(long userId) {
        return null;
    }
}
