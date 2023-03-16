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
import com.mobile.backend.service.ICartService;

@RestController
@RequestMapping("api/v1/carts")
public class CartController {

	@Autowired
	ICartService cartService;
	
	@GetMapping
	public ResponseEntity<List<CartResponse>> getAllCart(){
		List<CartResponse> carts = cartService.getAllCart();
		return new ResponseEntity<>(carts,HttpStatus.OK);
	}
	
	@GetMapping("/{cart_id}")
	public ResponseEntity<CartResponse> getCartById(
			@PathVariable("cart_id") Long cartId){
		CartResponse cart = cartService.getCartById(cartId);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}

}
