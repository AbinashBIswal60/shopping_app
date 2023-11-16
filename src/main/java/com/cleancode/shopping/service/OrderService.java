package com.cleancode.shopping.service;

import com.cleancode.shopping.payload.OrderRequest;
import com.cleancode.shopping.payload.OrderResponse;

public interface OrderService {

    String placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
