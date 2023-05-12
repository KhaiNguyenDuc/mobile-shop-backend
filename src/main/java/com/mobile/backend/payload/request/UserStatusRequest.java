package com.mobile.backend.payload.request;

import lombok.Data;

@Data
public class UserStatusRequest {

	private Long userId;
	private Boolean isEnabled;
}
