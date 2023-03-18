package com.mobile.backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Banner;
import com.mobile.backend.model.Image;
import com.mobile.backend.repository.BannerRepository;
import com.mobile.backend.repository.ImageRepository;
import com.mobile.backend.service.IBannerService;
import com.mobile.backend.untils.AppConstant;
import com.mobile.backend.untils.FileUploadUtils;

import io.jsonwebtoken.io.IOException;

@Service
public class BannerServiceImpl implements IBannerService {

	@Autowired
	BannerRepository bannerRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	
	@Override
	public Image getImagesById(Long categoryId) {
		Banner banner = bannerRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.BANNER_IMAGE_NOT_FOUND+categoryId));
		return banner.getImage();
		
	}

	@Override
	public Banner uploadImage(MultipartFile file, Long bannerId) throws IOException, java.io.IOException {
		Banner banner = bannerRepository.findById(bannerId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.BRAND_NOT_FOUND+bannerId));
		
		Image image;
		if(banner.getImage()!=null) {
			image = banner.getImage();
			if (!file.isEmpty()) {
				FileUploadUtils.saveBannerImage(file, bannerId);
				image.setTitle(bannerId.toString() + ".png");
				image.setPath(AppConstant.UPLOAD_BANNER_DIRECTORY+"/"+bannerId+".png");
				imageRepository.save(image);
				banner.setImage(image);
				bannerRepository.save(banner);
			}
		}else {
			image = new Image();
			if (!file.isEmpty()) {
				FileUploadUtils.saveBannerImage(file, bannerId);
				image.setTitle(bannerId.toString() + ".png");
				image.setPath(AppConstant.UPLOAD_BANNER_DIRECTORY+"/"+bannerId+".png");
				imageRepository.save(image);
				banner.setImage(image);
				bannerRepository.save(banner);
			}
		}
		
		
		return banner;
	}

	@Override
	public Banner addBanner(MultipartFile file) throws java.io.IOException {
		Banner banner = new Banner();
		Banner bannerSaved = bannerRepository.save(banner);
		
		Image image = new Image();
		image.setTitle(bannerSaved.getId()+".png");
		image.setPath(AppConstant.UPLOAD_BANNER_DIRECTORY+"/"+bannerSaved.getId()+".png");

		
		FileUploadUtils.saveBannerImage(file, bannerSaved.getId());
		imageRepository.save(image);
		banner.setImage(image);
		bannerRepository.save(bannerSaved);
		
		return banner;
	}

}
