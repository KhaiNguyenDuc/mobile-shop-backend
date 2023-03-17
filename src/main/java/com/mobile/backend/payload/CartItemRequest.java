package com.mobile.backend.payload;

import lombok.Data;

@Data
public class CartItemRequest {
	
	private Integer quantity;
	
	private Long choice_sizeId;
	
	private Long clothId;
	

}
