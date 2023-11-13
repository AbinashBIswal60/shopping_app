package com.cleancode.shopping.repository;

import com.cleancode.shopping.entity.CartDetailPK;
import com.cleancode.shopping.entity.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartDetails, CartDetailPK> {
}
