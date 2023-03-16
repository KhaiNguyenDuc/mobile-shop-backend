package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Size;
import com.mobile.backend.payload.BrandResponse;
import com.mobile.backend.payload.ClothResponse;
import com.mobile.backend.payload.SizeResponse;
import com.mobile.backend.repository.BrandRepository;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.repository.SizeRepository;
import com.mobile.backend.service.IClothService;

@Service
public class ClothServiceImpl implements IClothService {

	@Autowired
	ClothRepository clothRepository;
	
	@Autowired
	SizeRepository sizeRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
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
		Cloth cloth = clothRepository.findById(clothId).get();
		ClothResponse clothResponse = mapper.map(cloth, ClothResponse.class);
		return clothResponse;
	}

	@Override
	public List<SizeResponse> getSizesByClothId(Long clothId) {
		
		Cloth cloth = clothRepository.findById(clothId).get();
		
		List<Size> sizes = sizeRepository.findByClothes(cloth);
		List<SizeResponse> sizeResponses = 
				Arrays.asList(mapper.map(sizes, SizeResponse[].class));
		return sizeResponses;
	}

	@Override
	public BrandResponse getBrandByClothId(Long clothId) {
		
		Cloth cloth = clothRepository.findById(clothId).get();
		Brand brand = brandRepository.findByClothes(cloth);
		BrandResponse brandResponse = mapper.map(brand, BrandResponse.class);
		return brandResponse;
	}

}
