package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.SizeResponse;

public interface ISizeService {

	List<SizeResponse> getAllSizes();

	SizeResponse getSizeById(Long sizeId);

}
