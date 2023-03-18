package com.mobile.backend.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
	
	private String deliverMethod;
	
	private List<Long> cartItemId;
}
