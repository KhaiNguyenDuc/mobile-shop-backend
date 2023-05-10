package com.mobile.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.backend.payload.request.BrandRequest;
import com.mobile.backend.payload.response.BrandResponse;
import com.mobile.backend.payload.response.MattressResponse;
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
	public ResponseEntity<List<MattressResponse>> getClothesByBrandId(
			@PathVariable("brand_id") Long brandId){
		List<MattressResponse> clothResponses = brandService.getMattressesByBrandId(brandId);
		return new ResponseEntity<>(clothResponses,HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BrandResponse> addBrand(
			@RequestBody BrandRequest brandRequest){
		BrandResponse brand = brandService.addBrand(brandRequest);
		return new ResponseEntity<>(brand,HttpStatus.OK);
	} 
	
}
