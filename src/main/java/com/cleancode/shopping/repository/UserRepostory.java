package com.cleancode.shopping.repository;

import com.cleancode.shopping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepostory extends JpaRepository<User, Long> {
}
