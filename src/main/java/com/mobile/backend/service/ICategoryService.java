package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.request.CategoryRequest;
import com.mobile.backend.payload.response.CategoryResponse;
import com.mobile.backend.payload.response.ClothResponse;

public interface ICategoryService {

	List<CategoryResponse> getAllCategories();

	CategoryResponse getCategoryById(Long categoryId);

	List<ClothResponse> getClothesByCategoryId(Long categoryId);

	CategoryResponse addCategory(CategoryRequest categoryRequest);

}
