package com.mobile.backend.untils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;

public class FileUploadUtils {
	public static Path saveFile(String uploadDir,MultipartFile file) throws IOException, java.io.IOException {
		Path fileNameAndPath = Paths.get(uploadDir, file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());
		return fileNameAndPath;
    }
	public static Path saveCategoryImage(MultipartFile file, Long categoryId) throws IOException, java.io.IOException {
		Path fileNameAndPath = Paths.get(
				System.getProperty("user.dir") +
				AppConstant.UPLOAD_DIRECTORY+
				AppConstant.UPLOAD_CATEGORY_DIRECTORY, 
				categoryId.toString()+".png");
		Files.write(fileNameAndPath, file.getBytes());
		return fileNameAndPath;
	}
	public static Path saveClothImage(MultipartFile file, Long clothId) throws java.io.IOException {
		Path fileNameAndPath = Paths.get(
				System.getProperty("user.dir") +
				AppConstant.UPLOAD_DIRECTORY+
				AppConstant.UPLOAD_CLOTH_DIRECTORY, 
				clothId.toString()+".png");
		Files.write(fileNameAndPath, file.getBytes());
		return fileNameAndPath;
		
	}
	public static Path saveUserImage(MultipartFile file, Long userID) throws java.io.IOException {
		Path fileNameAndPath = Paths.get(
				System.getProperty("user.dir") +
				AppConstant.UPLOAD_DIRECTORY+
				AppConstant.UPLOAD_USER_DIRECTORY, 
				userID.toString()+".png");
		Files.write(fileNameAndPath, file.getBytes());
		return fileNameAndPath;
	}
	public static Path saveBannerImage(MultipartFile file, Long bannerId) throws java.io.IOException {
		Path fileNameAndPath = Paths.get(
				System.getProperty("user.dir") +
				AppConstant.UPLOAD_DIRECTORY+
				AppConstant.UPLOAD_BANNER_DIRECTORY, 
				bannerId.toString()+".png");
		Files.write(fileNameAndPath, file.getBytes());
		return fileNameAndPath;
		
	}
}
