package com.cleancode.shopping.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private String productName;
    private long quantity;
    private Instant orderDate;
    private String orderStatus;
    private Double totalAmount;
    private Instant expectedDeliveryDate;
}
