package com.mobile.backend.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Banner;
import com.mobile.backend.model.Image;
import com.mobile.backend.service.IBannerService;
import com.mobile.backend.untils.AppConstant;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("api/v1")
public class DynamicResourceController {

	@Autowired
	IBannerService bannerService;

	// Get Image
	@GetMapping(value = "/banners/{banner_id}/images", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<InputStreamResource> getImages(
			@PathVariable("banner_id") Long bannerId) {

		Image image = bannerService.getImagesById(bannerId);

		try {
			InputStream in = getClass().getResourceAsStream(image.getPath());

			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(in));
		} catch (Exception e) {
			throw new ResourceNotFoundException(AppConstant.BANNER_IMAGE_NOT_FOUND);
		}

	}
	
	@PostMapping("/banners/{banner_id}/upload-image")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Banner> uploadImage(
			@RequestParam("image") MultipartFile file,
			@PathVariable("banner_id") Long bannerId) throws IOException, java.io.IOException {
		Banner banner = bannerService.uploadImage(file,bannerId);
		return new ResponseEntity<>(banner,HttpStatus.OK);
	}
	
	@PostMapping("/banners")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Banner> addBanner(
			@RequestParam("image") MultipartFile file) throws java.io.IOException  {
		Banner banner = bannerService.addBanner(file);
		return new ResponseEntity<>(banner,HttpStatus.OK);
	}
}
