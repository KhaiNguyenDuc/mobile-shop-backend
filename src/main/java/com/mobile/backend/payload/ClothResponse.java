package com.mobile.backend.payload;

import java.util.List;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Inventory;
import com.mobile.backend.model.Size;

import lombok.Data;

@Data
public class ClothResponse {

	private Long id;
	
	private String name;
	
	private Integer price;
	
	private String description;

	private List<Size> size;
	
	private Inventory inventory;
	
	private Brand brand;
}
