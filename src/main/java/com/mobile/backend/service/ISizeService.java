package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.response.SizeResponse;

public interface ISizeService {

	List<SizeResponse> getAllSizes();

	SizeResponse getSizeById(Long sizeId);

}
