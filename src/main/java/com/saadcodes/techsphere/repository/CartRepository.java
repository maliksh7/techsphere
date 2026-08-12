package com.saadcodes.techsphere.repository;

import com.saadcodes.techsphere.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
