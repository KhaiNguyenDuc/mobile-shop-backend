package com.mobile.backend.payload.response;

import java.util.ArrayList;
import java.util.List;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Category;
import com.mobile.backend.model.Image;
import com.mobile.backend.model.Inventory;

import lombok.Data;

@Data
public class ClothResponse {

	private Long id;
	
	private String name;
	
	private Integer price;
	
	private String description;
	
	private List<Inventory> inventories;
	
	private Brand brand;
	
	private Category category;
	
	public void addInventory(Inventory inventory) {
		if(this.inventories == null) {
			this.inventories = new ArrayList<>();
		}
		inventories.add(inventory);
	}
	
	private Image image;
}
