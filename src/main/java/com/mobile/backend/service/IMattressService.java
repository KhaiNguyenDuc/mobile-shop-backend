package com.mobile.backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.model.Image;
import com.mobile.backend.payload.request.MattressRequest;
import com.mobile.backend.payload.response.BrandResponse;
import com.mobile.backend.payload.response.MattressResponse;

public interface IMattressService {

	List<MattressResponse> getAllMattresses();

	MattressResponse getMattressById(Long mattressId);

	BrandResponse getBrandByMattressId(Long mattressId);

	MattressResponse addMattress(MattressRequest mattressRequest);

	MattressResponse uploadImage(MultipartFile file, Long categoryId) throws IOException;

	Image getImagesById(Long mattressId);

	List<MattressResponse> getTop3LatesMattresses();

	List<MattressResponse> getTop3BestSeller();

	List<MattressResponse> searchProducts(String query);

}
