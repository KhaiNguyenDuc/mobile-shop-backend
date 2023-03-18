package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Category;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Image;
import com.mobile.backend.payload.request.CategoryRequest;
import com.mobile.backend.payload.response.CategoryResponse;
import com.mobile.backend.payload.response.ClothResponse;
import com.mobile.backend.repository.CategoryRepository;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.repository.ImageRepository;
import com.mobile.backend.service.ICategoryService;
import com.mobile.backend.untils.AppConstant;
import com.mobile.backend.untils.FileUploadUtils;

import io.jsonwebtoken.io.IOException;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ClothRepository clothRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
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
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND+categoryId));
		CategoryResponse categoryResponse = mapper.map(category, CategoryResponse.class);
		return categoryResponse;
	}

	@Override
	public List<ClothResponse> getClothesByCategoryId(Long categoryId) {
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND+categoryId));
		
		List<Cloth> clothes = clothRepository.findByCategory(category);
		List<ClothResponse> clothResponses =
				Arrays.asList(mapper.map(clothes, ClothResponse[].class));
		return clothResponses;
	}

	@Override
	public CategoryResponse addCategory(CategoryRequest categoryRequest) {
		
		Category category = new Category();
		category.setName(categoryRequest.getName());
		categoryRepository.save(category);
		
		CategoryResponse categoryResponse = mapper.map(category, CategoryResponse.class);
		return categoryResponse;
	}

	@Override
	public CategoryResponse uploadImage(MultipartFile file, Long categoryId) throws IOException, java.io.IOException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND+categoryId));
		Image image;
		if(category.getImage()!=null) {
			image = category.getImage();
			if (!file.isEmpty()) {
				FileUploadUtils.saveCategoryImage(file, categoryId);
				image.setTitle(categoryId.toString() + ".png");
				image.setPath(AppConstant.UPLOAD_CATEGORY_DIRECTORY+"/"+categoryId+".png");
				imageRepository.save(image);
				category.setImage(image);
				categoryRepository.save(category);
			}
		}else {
			image = new Image();
			if (!file.isEmpty()) {
				FileUploadUtils.saveCategoryImage(file, categoryId);
				image.setTitle(categoryId.toString() + ".png");
				image.setPath(AppConstant.UPLOAD_CATEGORY_DIRECTORY+"/"+categoryId+".png");
				imageRepository.save(image);
				category.setImage(image);
				categoryRepository.save(category);
			}
		}
		
		
		
		CategoryResponse categoryResponse = mapper.map(category, CategoryResponse.class);
		categoryResponse.setImagePath(image.getPath());
		return categoryResponse;
	}

	@Override
	public Image getImagesById(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND+categoryId));
		
		return category.getImage();
	}

}
