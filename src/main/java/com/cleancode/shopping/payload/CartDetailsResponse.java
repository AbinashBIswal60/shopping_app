package com.cleancode.shopping.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailsResponse {
    private long userId;
    private HashMap<ProductResponse, Long> products;
    private Double totalAmount;
    private String message;
}
