package com.mobile.backend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.model.Image;
import com.mobile.backend.payload.request.CategoryRequest;
import com.mobile.backend.payload.response.CategoryResponse;
import com.mobile.backend.payload.response.ClothResponse;

import io.jsonwebtoken.io.IOException;

public interface ICategoryService {

	List<CategoryResponse> getAllCategories();

	CategoryResponse getCategoryById(Long categoryId);

	List<ClothResponse> getClothesByCategoryId(Long categoryId);

	CategoryResponse addCategory(CategoryRequest categoryRequest);

	CategoryResponse uploadImage(MultipartFile file, Long categoryId) throws IOException, java.io.IOException;

	Image getImagesById(Long categoryId);

}
