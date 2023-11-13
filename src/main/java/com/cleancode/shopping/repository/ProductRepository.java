package com.cleancode.shopping.repository;

import com.cleancode.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
