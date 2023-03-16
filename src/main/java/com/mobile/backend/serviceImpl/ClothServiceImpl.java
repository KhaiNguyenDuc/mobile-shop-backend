package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.model.Cloth;
import com.mobile.backend.payload.ClothResponse;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.service.IClothService;

@Service
public class ClothServiceImpl implements IClothService {

	@Autowired
	ClothRepository clothRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<ClothResponse> getAllCloths() {
		List<Cloth> cloths = clothRepository.findAll();
		List<ClothResponse> clothResponses = 
				Arrays.asList(modelMapper.map(cloths, ClothResponse[].class));
		return clothResponses;
	}

}
