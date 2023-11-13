package com.cleancode.shopping.payload;

import com.cleancode.shopping.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailsResponse {
    private long userId;
    private HashMap<Product, Integer> products;
    private long totalAmount;
}
