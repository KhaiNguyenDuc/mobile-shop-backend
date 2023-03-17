package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.BrandResponse;
import com.mobile.backend.payload.ClothResponse;
import com.mobile.backend.payload.SizeResponse;

public interface IClothService {

	List<ClothResponse> getAllCloths();

	ClothResponse getClothById(Long clothId);

	BrandResponse getBrandByClothId(Long clothId);

}
