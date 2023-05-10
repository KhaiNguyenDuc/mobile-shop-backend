package com.mobile.backend.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Image;
import com.mobile.backend.payload.request.CategoryRequest;
import com.mobile.backend.payload.response.CategoryResponse;
import com.mobile.backend.payload.response.MattressResponse;
import com.mobile.backend.service.ICategoryService;
import com.mobile.backend.untils.AppConstant;

import io.jsonwebtoken.io.IOException;

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
	
	@GetMapping("/{category_id}/mattresses")
	public ResponseEntity<List<MattressResponse>> getMattressesByCategoryId(
			@PathVariable("category_id") Long categoryId){
		List<MattressResponse> mattresses = categoryService.getMattressesByCategoryId(categoryId);
		return new ResponseEntity<>(mattresses,HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryResponse> addCategory(
			@RequestBody CategoryRequest categoryRequest){
		CategoryResponse category = categoryService.addCategory(categoryRequest);
		return new ResponseEntity<>(category,HttpStatus.OK);
	} 
	
	@PostMapping("/{category_id}/upload-image")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryResponse> uploadImage(
			@RequestParam("image") MultipartFile file,
			@PathVariable("category_id") Long categoryId) throws IOException, java.io.IOException {
		CategoryResponse category = categoryService.uploadImage(file,categoryId);
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	

	// Get Image
	@GetMapping(value = "/{category_id}/images", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<InputStreamResource> getImages(@PathVariable("category_id") Long categoryId) {

		Image image = categoryService.getImagesById(categoryId);

		try {
			InputStream in = getClass().getResourceAsStream(image.getPath());

			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(in));
		} catch (Exception e) {
			throw new ResourceNotFoundException(AppConstant.CATEGORY_IMAGE_NOT_FOUND);
		}

	}
	
	
}
