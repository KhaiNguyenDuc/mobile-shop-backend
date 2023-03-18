package com.mobile.backend.payload.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String image;
	private LocalDate birthday;
	private String address;
}
