package com.cleancode.shopping.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CartDetailPK implements Serializable {
    private long userId;
    private long productId;

}
