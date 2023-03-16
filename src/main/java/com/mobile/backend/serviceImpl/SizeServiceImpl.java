package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Size;
import com.mobile.backend.payload.SizeResponse;
import com.mobile.backend.repository.SizeRepository;
import com.mobile.backend.service.ISizeService;
import com.mobile.backend.untils.AppConstant;

@Service
public class SizeServiceImpl implements ISizeService {

	@Autowired
	SizeRepository sizeRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<SizeResponse> getAllSizes() {
		List<Size> size = sizeRepository.findAll();
		List<SizeResponse> sizeResponses =
				Arrays.asList(mapper.map(size, SizeResponse[].class));
		return sizeResponses;
	}

	@Override
	public SizeResponse getSizeById(Long sizeId) {
		Size size = sizeRepository.findById(sizeId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.SIZE_NOT_FOUND+sizeId));;
		SizeResponse sizeResponse = mapper.map(size, SizeResponse.class);
		return sizeResponse;
	}

}
