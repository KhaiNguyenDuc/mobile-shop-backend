package com.mobile.backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.model.Image;
import com.mobile.backend.payload.request.ClothRequest;
import com.mobile.backend.payload.response.BrandResponse;
import com.mobile.backend.payload.response.ClothResponse;

public interface IClothService {

	List<ClothResponse> getAllCloths();

	ClothResponse getClothById(Long clothId);

	BrandResponse getBrandByClothId(Long clothId);

	ClothResponse addCloth(ClothRequest clothRequest);

	ClothResponse uploadImage(MultipartFile file, Long categoryId) throws IOException;

	Image getImagesById(Long clothId);

}
