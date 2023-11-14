package com.cleancode.shopping.service;

import com.cleancode.shopping.entity.CartDetails;
import com.cleancode.shopping.payload.CartDetailsResponse;


public interface CartService {
    CartDetailsResponse viewCart(long userId);

    String addToCart(CartDetails cartDetails);

    String reduceQuantityOfAProduct(long userId, long productId);

    String removeAProduct(long userId, long productId);

    String removeAllFromCart(long userId);

}
