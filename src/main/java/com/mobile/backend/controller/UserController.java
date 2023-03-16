package com.mobile.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.CartResponse;
import com.mobile.backend.payload.OrderResponse;
import com.mobile.backend.payload.UserResponse;
import com.mobile.backend.service.IUserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	IUserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		
		List<UserResponse> users = userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("/{user_id}")
	public ResponseEntity<UserResponse> getUserById(
			@PathVariable("user_id") Long userId){
		UserResponse user = userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/{user_id}/cart")
	public ResponseEntity<CartResponse> getCartByUserId(
			@PathVariable("user_id") Long userId){
		CartResponse cart = userService.getCartByUserId(userId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	@GetMapping("/{user_id}/orders")
	public ResponseEntity<List<OrderResponse>> getOrdersByUserId(
			@PathVariable("user_id") Long userId){
		List<OrderResponse> orders = userService.getOrdersByUserId(userId);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
}
