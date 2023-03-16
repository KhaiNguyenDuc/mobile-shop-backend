package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.CategoryResponse;
import com.mobile.backend.payload.ClothResponse;

public interface ICategoryService {

	List<CategoryResponse> getAllCategories();

	CategoryResponse getCategoryById(Long categoryId);

	List<ClothResponse> getClothesByCategoryId(Long categoryId);

}
