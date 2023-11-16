package com.cleancode.shopping.repository;

import com.cleancode.shopping.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetails,Long> {
}
