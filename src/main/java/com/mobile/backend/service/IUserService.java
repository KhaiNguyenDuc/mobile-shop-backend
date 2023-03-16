package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.CartResponse;
import com.mobile.backend.payload.OrderResponse;
import com.mobile.backend.payload.UserResponse;

public interface IUserService {

	List<UserResponse> getAllUsers();

	UserResponse getUserById(Long userId);

	CartResponse getCartByUserId(Long userId);

	List<OrderResponse> getOrdersByUserId(Long userId);

}
