package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.model.Category;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.payload.CategoryResponse;
import com.mobile.backend.payload.ClothResponse;
import com.mobile.backend.repository.CategoryRepository;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ClothRepository clothRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<CategoryResponse> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryResponse> categoryResponses =
				Arrays.asList(mapper.map(categories, CategoryResponse[].class));
		return categoryResponses;
	}

	@Override
	public CategoryResponse getCategoryById(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).get();
		CategoryResponse categoryResponse = mapper.map(category, CategoryResponse.class);
		return categoryResponse;
	}

	@Override
	public List<ClothResponse> getClothesByCategoryId(Long categoryId) {
		
		Category category = categoryRepository.findById(categoryId).get();
		
		List<Cloth> clothes = clothRepository.findByCategory(category);
		List<ClothResponse> clothResponses =
				Arrays.asList(mapper.map(clothes, ClothResponse[].class));
		return clothResponses;
	}

}
