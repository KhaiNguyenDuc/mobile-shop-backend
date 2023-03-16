package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.payload.BrandResponse;
import com.mobile.backend.payload.ClothResponse;
import com.mobile.backend.repository.BrandRepository;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.service.IBrandService;

@Service
public class BrandServiceImpl implements IBrandService{

	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	ClothRepository clothRepository;
	
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
		Brand brand = brandRepository.findById(brandId).get();
		BrandResponse brandResponse = mapper.map(brand, BrandResponse.class);
		return brandResponse;
		
	}

	@Override
	public List<ClothResponse> getClothesByBrandId(Long brandId) {
		Brand brand = brandRepository.findById(brandId).get();
		List<Cloth> clothes = clothRepository.findByBrand(brand);
		List<ClothResponse> clothResponses = 
				Arrays.asList(mapper.map(clothes, ClothResponse[].class));
		return clothResponses;
	}

}
