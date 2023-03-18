package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.request.ClothRequest;
import com.mobile.backend.payload.response.BrandResponse;
import com.mobile.backend.payload.response.ClothResponse;

public interface IClothService {

	List<ClothResponse> getAllCloths();

	ClothResponse getClothById(Long clothId);

	BrandResponse getBrandByClothId(Long clothId);

	ClothResponse addCloth(ClothRequest clothRequest);

}
