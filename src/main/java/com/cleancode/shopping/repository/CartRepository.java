package com.cleancode.shopping.repository;

import com.cleancode.shopping.entity.CartDetailPK;
import com.cleancode.shopping.entity.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartDetails, CartDetailPK> {
    List<CartDetails> findByUserId(long userId);
    long deleteByUserId(long userId);
}
