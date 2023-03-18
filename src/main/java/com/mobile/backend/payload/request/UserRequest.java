package com.mobile.backend.payload.request;

import java.util.Date;

import lombok.Data;

@Data
public class UserRequest {
	
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Date birthday;
	private String address;
	private String image;
}
