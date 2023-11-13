package com.cleancode.shopping.service;

import com.cleancode.shopping.payload.ProductRequest;
import com.cleancode.shopping.payload.ProductResponse;


public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);

    public void deleteProductById(long productId);
}
