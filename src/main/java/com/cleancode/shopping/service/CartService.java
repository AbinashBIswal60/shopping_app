package com.cleancode.shopping.service;

import com.cleancode.shopping.entity.CartDetails;
import com.cleancode.shopping.payload.CartDetailsResponse;
import org.springframework.stereotype.Service;


public interface CartService {
    CartDetailsResponse viewCart(String userId);

    String addToCart(CartDetails cartDetails);

    String reduceQuantityOfAProduct(long userId, long productId);

    String removeAProduct(long userId, long productId);

    String removeAllFromCart(long userId);

}
