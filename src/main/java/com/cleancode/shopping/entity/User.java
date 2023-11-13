package com.cleancode.shopping.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Nonnull
    @Column(name = "USER_NAME")
    private String userName;

    @Nonnull
    @Column(name = "PHONE_NUMBER")
    private long phoneNumber;

}
