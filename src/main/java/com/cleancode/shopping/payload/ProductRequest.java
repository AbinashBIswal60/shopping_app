package com.cleancode.shopping.payload;

import lombok.Builder;
import lombok.Data;

import java.sql.Blob;

@Data
@Builder
public class ProductRequest {
    private String productName;
    private Double price;
    private long quantity;
    private Blob image;
}
