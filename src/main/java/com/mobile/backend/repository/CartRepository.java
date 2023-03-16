package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
