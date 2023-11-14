package com.cleancode.shopping.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "productName",columnNames = "PRODUCT_NAME")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private long productId;

    @Column(name = "PRODUCT_NAME",nullable = false)
    private String productName;

    @Nonnull
    private Double price;

    @Nonnull
    private long quantity;

    private Blob image;
}
