package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Mattress;
import com.mobile.backend.payload.request.BrandRequest;
import com.mobile.backend.payload.response.BrandResponse;
import com.mobile.backend.payload.response.MattressResponse;
import com.mobile.backend.repository.BrandRepository;
import com.mobile.backend.repository.MattressRepository;
import com.mobile.backend.service.IBrandService;
import com.mobile.backend.untils.AppConstant;

@Service
public class BrandServiceImpl implements IBrandService{

	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	MattressRepository clothRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<BrandResponse> getAllBrands() {
		List<Brand> brands =  brandRepository.findAll();
		List<BrandResponse> brandResponses = 
				Arrays.asList(mapper.map(brands, BrandResponse[].class));
		return brandResponses;
	}

	@Override
	public BrandResponse getBrandById(Long brandId) {
		Brand brand = brandRepository.findById(brandId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.BRAND_NOT_FOUND+brandId));
		BrandResponse brandResponse = mapper.map(brand, BrandResponse.class);
		return brandResponse;
		
	}

	@Override
	public List<MattressResponse> getMattressesByBrandId(Long brandId) {
		Brand brand = brandRepository.findById(brandId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.BRAND_NOT_FOUND+brandId));
		List<Mattress> clothes = clothRepository.findByBrand(brand);
		List<MattressResponse> clothResponses = 
				Arrays.asList(mapper.map(clothes, MattressResponse[].class));
		return clothResponses;
	}

	@Override
	public BrandResponse addBrand(BrandRequest brandRequest) {
		Brand brand = new Brand();
		brand.setName(brandRequest.getName());
		brandRepository.save(brand);
		BrandResponse brandResponse = mapper.map(brand, BrandResponse.class);
		return brandResponse;
	}

}
