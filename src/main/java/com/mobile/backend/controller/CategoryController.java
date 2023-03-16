package com.mobile.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.CategoryResponse;
import com.mobile.backend.payload.ClothResponse;
import com.mobile.backend.service.ICategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryResponse>> getAllCategories(){
		List<CategoryResponse> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	
	@GetMapping("/{category_id}")
	public ResponseEntity<CategoryResponse> getCategoryById(
			@PathVariable("category_id") Long categoryId){
		CategoryResponse category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	
	@GetMapping("/{category_id}/clothes")
	public ResponseEntity<List<ClothResponse>> getClothesByCategoryId(
			@PathVariable("category_id") Long categoryId){
		List<ClothResponse> clothes = categoryService.getClothesByCategoryId(categoryId);
		return new ResponseEntity<>(clothes,HttpStatus.OK);
	}
}
