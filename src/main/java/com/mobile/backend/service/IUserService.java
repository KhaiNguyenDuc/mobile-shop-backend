package com.mobile.backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.model.Image;
import com.mobile.backend.payload.request.UserProfileRequest;
import com.mobile.backend.payload.request.UserRequest;
import com.mobile.backend.payload.request.UserStatusRequest;
import com.mobile.backend.payload.response.CartResponse;
import com.mobile.backend.payload.response.OrderResponse;
import com.mobile.backend.payload.response.UserProfileResponse;
import com.mobile.backend.payload.response.UserResponse;
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

	UserProfileResponse updateCurrentProfile(UserPrincipal userPrincipal, UserProfileRequest userProfileRequest);

	UserProfileResponse uploadImage(MultipartFile file, UserPrincipal userPrincipal) throws IOException;

	Image getImagesById(UserPrincipal userPrincipal);

	UserResponse updateUserStatus(UserStatusRequest userStatusRequest);


}
