package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.BrandResponse;
import com.mobile.backend.payload.ClothResponse;

public interface IBrandService {

	List<BrandResponse> getAllBrands();

	BrandResponse getBrandById(Long brandId);

	List<ClothResponse> getClothesByBrandId(Long brandId);

}
