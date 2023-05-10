package com.mobile.backend.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class MattressRequest {


	private String name;
	
	private Double price;
	
	private String description;
	
	private List<InventoryRequest> inventory;
	
	private Long brandId;
	
	private Long categoryId;
}
