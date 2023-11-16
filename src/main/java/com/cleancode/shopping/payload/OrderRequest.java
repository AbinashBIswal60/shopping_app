package com.cleancode.shopping.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private long userId;
    private long productId;
    private long quantity;
    private Double totalAmount;
    private String paymentMode;
}
