package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.CartResponse;

public interface ICartService {

	CartResponse getCartById(Long cartId);

	List<CartResponse> getAllCart();

}
