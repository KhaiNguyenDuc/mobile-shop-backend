package com.mobile.backend.payload.request;

import lombok.Data;

@Data
public class OrderUpdateRequest {

	private Long orderId;
	private String status;
}
