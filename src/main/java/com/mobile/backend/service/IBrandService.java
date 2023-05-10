package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.request.BrandRequest;
import com.mobile.backend.payload.response.BrandResponse;
import com.mobile.backend.payload.response.MattressResponse;

public interface IBrandService {

	List<BrandResponse> getAllBrands();

	BrandResponse getBrandById(Long brandId);

	List<MattressResponse> getMattressesByBrandId(Long brandId);

	BrandResponse addBrand(BrandRequest brandRequest);

}
