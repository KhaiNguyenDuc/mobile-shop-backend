package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.CartResponse;
import com.mobile.backend.payload.OrderResponse;
import com.mobile.backend.payload.UserProfileResponse;
import com.mobile.backend.payload.UserRequest;
import com.mobile.backend.payload.UserResponse;
import com.mobile.backend.security.UserPrincipal;

public interface IUserService {

	List<UserResponse> getAllUsers();

	UserResponse getUserById(Long userId);

	CartResponse getCartByUserId(Long userId);

	List<OrderResponse> getOrdersByUserId(Long userId);

	UserProfileResponse createUser(UserRequest userRequest);

	UserProfileResponse getCurrentUser(UserPrincipal userPrincipal);

	Boolean checkUsernameUnique(String username);

	Boolean checkEmailUnique(String email);

	CartResponse getCartByCurrentUser(UserPrincipal userPrincipal);

	List<OrderResponse> getOrdersByCurrentUser(UserPrincipal userPrincipal);


}
