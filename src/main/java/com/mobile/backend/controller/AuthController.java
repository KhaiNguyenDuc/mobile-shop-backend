package com.mobile.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.request.LoginRequest;
import com.mobile.backend.payload.request.UserRequest;
import com.mobile.backend.payload.response.JwtResponse;
import com.mobile.backend.payload.response.UserProfileResponse;
import com.mobile.backend.security.JwtTokenProvider;
import com.mobile.backend.service.IUserService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	@Autowired
	IUserService userService;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> signin(
			@RequestBody LoginRequest loginRequest){
		
		UsernamePasswordAuthenticationToken authReq
		 = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		Authentication auth = authManager.authenticate(authReq);
		String token = tokenProvider.generateToken(auth);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return new ResponseEntity<>(new JwtResponse(token,"jwt"),HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserProfileResponse> register(
			@RequestBody UserRequest userRequest
			){
		UserProfileResponse userProfile = userService.createUser(userRequest);
		return new ResponseEntity<>(userProfile,HttpStatus.CREATED);
	}
}
