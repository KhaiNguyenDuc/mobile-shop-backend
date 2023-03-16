package com.mobile.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.UserResponse;
import com.mobile.backend.service.IUserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	IUserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers(){
		
		List<UserResponse> users = userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
}
