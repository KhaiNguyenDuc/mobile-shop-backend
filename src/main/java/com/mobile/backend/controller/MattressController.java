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
import com.mobile.backend.payload.request.MattressRequest;
import com.mobile.backend.payload.response.BrandResponse;
import com.mobile.backend.payload.response.MattressResponse;
import com.mobile.backend.service.IMattressService;
import com.mobile.backend.untils.AppConstant;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/mattresses")
@Slf4j
public class MattressController {

	@Autowired
	IMattressService mattressService;
	
	@GetMapping
	public ResponseEntity<List<MattressResponse>> getAllMattresses(){
		List<MattressResponse> mattresses = mattressService.getAllMattresses();
		return new ResponseEntity<>(mattresses,HttpStatus.OK);
	}
	
	@GetMapping("/{mattress_id}")
	public ResponseEntity<MattressResponse> getMattressById(
			@PathVariable("mattress_id") Long mattressId){
		MattressResponse mattress = mattressService.getMattressById(mattressId);
		return new ResponseEntity<>(mattress,HttpStatus.OK);
	}
	
	@GetMapping("/{mattress_id}/brand")
	public ResponseEntity<BrandResponse> getBrandByMattressId(
			@PathVariable("mattress_id") Long mattressId){
		BrandResponse brand = mattressService.getBrandByMattressId(mattressId);
		return new ResponseEntity<>(brand,HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MattressResponse> addMattress(
			@RequestBody MattressRequest mattressRequest){
		MattressResponse mattress = mattressService.addMattress(mattressRequest);
		return new ResponseEntity<>(mattress,HttpStatus.OK);
	}
	
	@PostMapping("/{mattress_id}/upload-image")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MattressResponse> uploadImage(
			@RequestParam("image") MultipartFile file,
			@PathVariable("mattress_id") Long categoryId) throws IOException, java.io.IOException {
		MattressResponse mattress = mattressService.uploadImage(file,categoryId);
		return new ResponseEntity<>(mattress,HttpStatus.OK);
	}
	
	// Get Image
	@GetMapping(value = "/{mattress_id}/images", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<InputStreamResource> getImages(@PathVariable("mattress_id") Long mattressId) {

		Image image = mattressService.getImagesById(mattressId);

		try {
			InputStream in = getClass().getResourceAsStream(image.getPath());

			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(in));
		} catch (Exception e) {
			throw new ResourceNotFoundException(AppConstant.MATTRESS_IMAGE_NOT_FOUND);
		}

	}
	
	@GetMapping(value="/latest")
	public ResponseEntity<List<MattressResponse>> getTop3LatestMattress(){
		List<MattressResponse> mattresses = mattressService.getTop3LatesMattresses();
		return new ResponseEntity<>(mattresses,HttpStatus.OK);
	}
	
	@GetMapping(value="/best-seller")
	public ResponseEntity<List<MattressResponse>> getTop3BestSeller(){
		List<MattressResponse> mattresses = mattressService.getTop3BestSeller();
		return new ResponseEntity<>(mattresses,HttpStatus.OK);
	}
}
