package com.mobile.backend.payload;

import java.util.List;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Inventory;

import lombok.Data;

@Data
public class ClothResponse {

	private Long id;
	
	private String name;
	
	private Integer price;
	
	private String description;
	
	private List<Inventory> inventory;
	
	private Brand brand;
}
