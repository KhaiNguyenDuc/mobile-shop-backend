package com.mobile.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.BrandResponse;
import com.mobile.backend.payload.ClothResponse;
import com.mobile.backend.service.IBrandService;

@RestController
@RequestMapping("api/v1/brands")
public class BrandController {

	@Autowired
	IBrandService brandService;
	
	@GetMapping
	public ResponseEntity<List<BrandResponse>> getAllBrands(){
		List<BrandResponse> brands = brandService.getAllBrands();
		return new ResponseEntity<>(brands,HttpStatus.OK);
	}
	
	@GetMapping("/{brand_id}")
	public ResponseEntity<BrandResponse> getBrandById(
			@PathVariable("brand_id") Long brandId){
		BrandResponse brand = brandService.getBrandById(brandId);
		return new ResponseEntity<>(brand,HttpStatus.OK);
	}
	
	@GetMapping("/{brand_id}/clothes")
	public ResponseEntity<List<ClothResponse>> getClothesByBrandId(
			@PathVariable("brand_id") Long brandId){
		List<ClothResponse> clothResponses = brandService.getClothesByBrandId(brandId);
		return new ResponseEntity<>(clothResponses,HttpStatus.OK);
	}
	
}
