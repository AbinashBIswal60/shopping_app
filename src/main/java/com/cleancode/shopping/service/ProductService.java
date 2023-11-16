package com.cleancode.shopping.service;

import com.cleancode.shopping.payload.ProductRequest;
import com.cleancode.shopping.payload.ProductResponse;

import java.util.List;


public interface ProductService {
    String addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductByName(String productName);

    public void deleteProductById(long productId);
}
