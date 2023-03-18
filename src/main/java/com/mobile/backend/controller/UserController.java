package com.mobile.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.request.UserProfileRequest;
import com.mobile.backend.payload.response.CartResponse;
import com.mobile.backend.payload.response.OrderResponse;
import com.mobile.backend.payload.response.UserProfileResponse;
import com.mobile.backend.payload.response.UserResponse;
import com.mobile.backend.security.CurrentUser;
import com.mobile.backend.security.UserPrincipal;
import com.mobile.backend.service.IUserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	IUserService userService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		
		List<UserResponse> users = userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("/{user_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserResponse> getUserById(
			@PathVariable("user_id") Long userId){
		UserResponse user = userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/{user_id}/cart")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CartResponse> getCartByUserId(
			@PathVariable("user_id") Long userId){
		CartResponse cart = userService.getCartByUserId(userId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	@GetMapping("/{user_id}/orders")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<OrderResponse>> getOrdersByUserId(
			@PathVariable("user_id") Long userId){
		List<OrderResponse> orders = userService.getOrdersByUserId(userId);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/current")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserProfileResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		UserProfileResponse userProfile = userService.getCurrentUser(userPrincipal);
		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}
	
	@GetMapping("/current/cart")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<CartResponse> getCartByCurrentUser(@CurrentUser UserPrincipal userPrincipal){
		CartResponse cart = userService.getCartByCurrentUser(userPrincipal);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	@GetMapping("/current/orders")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<OrderResponse>> getOrdersByCurrentUser(@CurrentUser UserPrincipal userPrincipal){
		List<OrderResponse> orders = userService.getOrdersByCurrentUser(userPrincipal);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/unique-username")
	public ResponseEntity<Boolean> checkUsernameUnique(@RequestParam("username") String username) {

		Boolean unique = userService.checkUsernameUnique(username);

		return new ResponseEntity<>(unique, HttpStatus.OK);
	}

	@GetMapping("/unique-email")
	public ResponseEntity<Boolean> checkEmailUnique(@RequestParam("email") String email) {

		Boolean unique = userService.checkEmailUnique(email);
		return new ResponseEntity<>(unique, HttpStatus.OK);
	}
	
	@PatchMapping("/current/profile")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserProfileResponse> updateCurrentProfile(
			@CurrentUser UserPrincipal userPrincipal,
			@RequestBody UserProfileRequest userProfileRequest) {

		UserProfileResponse user = userService.updateCurrentProfile(userPrincipal,userProfileRequest);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
