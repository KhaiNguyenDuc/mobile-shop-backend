package com.mobile.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.OrderResponse;
import com.mobile.backend.security.CurrentUser;
import com.mobile.backend.security.UserPrincipal;
import com.mobile.backend.service.IOrderService;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

	@Autowired
	IOrderService orderService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<OrderResponse>> getAllOrders(){
		List<OrderResponse> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/{order_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<OrderResponse> getOrderById(
			@PathVariable("order_id") Long orderId){
		OrderResponse order = orderService.getOrderById(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@GetMapping("/current")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<OrderResponse>> getCurrentOrderByUserId(@CurrentUser UserPrincipal userPrincipal){
		List<OrderResponse> orders = orderService.getCurrentOrderByUserId(userPrincipal.getId());
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
}
