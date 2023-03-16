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
import com.mobile.backend.payload.SizeResponse;
import com.mobile.backend.service.IClothService;

@RestController
@RequestMapping("api/v1/clothes")
public class ClothController {

	@Autowired
	IClothService clothService;
	
	@GetMapping
	public ResponseEntity<List<ClothResponse>> getAllClothes(){
		List<ClothResponse> cloths = clothService.getAllCloths();
		return new ResponseEntity<>(cloths,HttpStatus.OK);
	}
	
	@GetMapping("/{cloth_id}")
	public ResponseEntity<ClothResponse> getClothById(
			@PathVariable("cloth_id") Long clothId){
		ClothResponse cloth = clothService.getClothById(clothId);
		return new ResponseEntity<>(cloth,HttpStatus.OK);
	}
	
	@GetMapping("/{cloth_id}/sizes")
	public ResponseEntity<List<SizeResponse>> getSizesByClothId(
			@PathVariable("cloth_id") Long clothId){
		List<SizeResponse> sizes = clothService.getSizesByClothId(clothId);
		return new ResponseEntity<>(sizes,HttpStatus.OK);
	}
	
	@GetMapping("/{cloth_id}/brand")
	public ResponseEntity<BrandResponse> getBrandByClothId(
			@PathVariable("cloth_id") Long clothId){
		BrandResponse brand = clothService.getBrandByClothId(clothId);
		return new ResponseEntity<>(brand,HttpStatus.OK);
	}
}
