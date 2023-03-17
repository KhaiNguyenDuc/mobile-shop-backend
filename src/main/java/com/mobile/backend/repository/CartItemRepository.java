package com.mobile.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.cart.Cart;
import com.mobile.backend.model.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


	Optional<CartItem> findByCartAndId(Cart cart, Long id);

}
