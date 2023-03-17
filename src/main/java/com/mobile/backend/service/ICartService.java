package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.CartResponse;
import com.mobile.backend.payload.ApiResponse;
import com.mobile.backend.payload.CartItemRequest;

public interface ICartService {

	CartResponse getCartById(Long cartId);

	List<CartResponse> getAllCart();

	CartResponse getCurrentCartByUserId(Long userId);

	CartResponse addCartItemByUserId(Long id, CartItemRequest itemRequest);

	ApiResponse deleteCartItemById(Long id, Long cartItemId);

	CartResponse updateCartItemById(Long id, Long cartItemId, CartItemRequest itemRequest);

}
