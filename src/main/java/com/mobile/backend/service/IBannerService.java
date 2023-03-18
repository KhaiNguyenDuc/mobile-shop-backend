package com.mobile.backend.service;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.model.Banner;
import com.mobile.backend.model.Image;

import io.jsonwebtoken.io.IOException;

public interface IBannerService {

	Image getImagesById(Long categoryId);

	Banner uploadImage(MultipartFile file, Long bannerId) throws IOException, java.io.IOException;

	Banner addBanner(MultipartFile file) throws java.io.IOException;


}
