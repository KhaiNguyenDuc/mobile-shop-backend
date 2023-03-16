package com.mobile.backend.payload;

import java.util.List;

import com.mobile.backend.model.cart.CartItem;

import lombok.Data;

@Data
public class CartResponse {
	
	private Long id;
	
	private List<CartItem> cartItems;
	
	private Long userId;
}
