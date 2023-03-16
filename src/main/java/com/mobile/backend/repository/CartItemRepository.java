package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
