package com.cleancode.shopping.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "CART_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CartDetailPK.class)
public class CartDetails {

    @Id
    @Column(name = "USER_ID")
    private long userId;

    @Id
    @Column(name = "PRODUCT_ID")
    private long productId;

    @Nonnull
    private long quantity;

}
