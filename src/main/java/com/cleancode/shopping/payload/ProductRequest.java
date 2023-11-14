package com.cleancode.shopping.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String name;
    private Double price;
    private long quantity;
}
