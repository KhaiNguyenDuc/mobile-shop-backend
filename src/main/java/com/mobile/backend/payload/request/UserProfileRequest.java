package com.mobile.backend.payload.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserProfileRequest {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private LocalDate birthday;
	private String address;
	private String image;
}
