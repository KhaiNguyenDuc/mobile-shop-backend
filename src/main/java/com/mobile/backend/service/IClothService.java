package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.ClothResponse;

public interface IClothService {

	List<ClothResponse> getAllCloths();

}
