package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Size;
import com.mobile.backend.payload.BrandResponse;
import com.mobile.backend.payload.ClothResponse;
import com.mobile.backend.payload.SizeResponse;
import com.mobile.backend.repository.BrandRepository;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.repository.InventoryRepository;
import com.mobile.backend.repository.SizeRepository;
import com.mobile.backend.service.IClothService;
import com.mobile.backend.untils.AppConstant;

@Service
public class ClothServiceImpl implements IClothService {

	@Autowired
	ClothRepository clothRepository;
	
	@Autowired
	SizeRepository sizeRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<ClothResponse> getAllCloths() {
		List<Cloth> clothes = clothRepository.findAll();
		List<ClothResponse> clothResponses = 
				Arrays.asList(mapper.map(clothes, ClothResponse[].class));
		return clothResponses;
	}

	@Override
	public ClothResponse getClothById(Long clothId) {
		Cloth cloth = clothRepository.findById(clothId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CLOTH_NOT_FOUND+clothId));
		ClothResponse clothResponse = mapper.map(cloth, ClothResponse.class);
		return clothResponse;
	}

	

	@Override
	public BrandResponse getBrandByClothId(Long clothId) {
		
		Cloth cloth = clothRepository.findById(clothId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CLOTH_NOT_FOUND+clothId));
		Brand brand = brandRepository.findByClothes(cloth)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.BRAND_NOT_FOUND+ clothId));
		BrandResponse brandResponse = mapper.map(brand, BrandResponse.class);
		return brandResponse;
	}

}
