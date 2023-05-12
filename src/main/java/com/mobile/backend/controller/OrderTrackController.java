package com.mobile.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.response.OrderTrackResponse;
import com.mobile.backend.service.IOrderTrackService;

@RestController
@RequestMapping("/api/v1/order-track")
public class OrderTrackController {
	
	@Autowired
	IOrderTrackService orderTrackService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<OrderTrackResponse>> getAllOrderTracks(){
		
		List<OrderTrackResponse> orderTracks =  orderTrackService.getAllOrderTracks();
		return new ResponseEntity<>(orderTracks, HttpStatus.OK);
	}
}
