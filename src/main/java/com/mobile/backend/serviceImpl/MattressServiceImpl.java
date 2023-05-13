package com.mobile.backend.serviceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Category;
import com.mobile.backend.model.Mattress;
import com.mobile.backend.model.Image;
import com.mobile.backend.model.Inventory;
import com.mobile.backend.model.Size;
import com.mobile.backend.payload.request.MattressRequest;
import com.mobile.backend.payload.request.InventoryRequest;
import com.mobile.backend.payload.response.BrandResponse;
import com.mobile.backend.payload.response.MattressResponse;
import com.mobile.backend.repository.BrandRepository;
import com.mobile.backend.repository.CategoryRepository;
import com.mobile.backend.repository.ImageRepository;
import com.mobile.backend.repository.InventoryRepository;
import com.mobile.backend.repository.MattressRepository;
import com.mobile.backend.repository.SizeRepository;
import com.mobile.backend.service.IMattressService;
import com.mobile.backend.untils.AppConstant;
import com.mobile.backend.untils.FileUploadUtils;

@Service
public class MattressServiceImpl implements IMattressService {

	@Autowired
	MattressRepository mattressRepository;
	
	@Autowired
	SizeRepository sizeRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<MattressResponse> getAllMattresses() {
		List<Mattress> mattresses = mattressRepository.findAll();
		List<MattressResponse> mattressResponses = 
				Arrays.asList(mapper.map(mattresses, MattressResponse[].class));
		return mattressResponses;
	}

	@Override
	public MattressResponse getMattressById(Long mattressId) {
		Mattress mattress = mattressRepository.findById(mattressId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.MATTRESS_NOT_FOUND+mattressId));
		MattressResponse clothResponse = mapper.map(mattress, MattressResponse.class);
		return clothResponse;
	}

	

	@Override
	public BrandResponse getBrandByMattressId(Long mattressId) {
		
		Mattress mattress = mattressRepository.findById(mattressId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.MATTRESS_NOT_FOUND+mattressId));
		Brand brand = brandRepository.findByMattress(mattress)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.BRAND_NOT_FOUND+ mattressId));
		BrandResponse brandResponse = mapper.map(brand, BrandResponse.class);
		return brandResponse;
	}

	@Override
	public MattressResponse addMattress(MattressRequest mattressRequest) {
		
		Brand brand = brandRepository.findById(mattressRequest.getBrandId())
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.BRAND_NOT_FOUND+mattressRequest.getBrandId()));
		Category category = categoryRepository.findById(mattressRequest.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND+mattressRequest.getCategoryId()));
		
		Mattress mattress = new Mattress();
		mattress.setBrand(brand);
		mattress.setCategory(category);
		mattress.setDescription(mattressRequest.getDescription());
		mattress.setName(mattressRequest.getName());
		mattress.setPrice(mattressRequest.getPrice());
		
		Mattress mattressSaved = mattressRepository.save(mattress);
		MattressResponse mattressResponse = mapper.map(mattressSaved, MattressResponse.class);
		
		for (InventoryRequest inventory : mattressRequest.getInventory()) {
			Inventory newInventory = new Inventory();
			newInventory.setQuantity(inventory.getQuantity());
			
			Size size = sizeRepository.findById(inventory.getSizeId())
					.orElseThrow(() -> new ResourceNotFoundException(AppConstant.SIZE_NOT_FOUND+inventory.getSizeId()));
			newInventory.setSize(size);
			newInventory.setMattress(mattressSaved);
			inventoryRepository.save(newInventory);
			
			mattressResponse.addInventory(newInventory);
		}
		
		
		
		return mattressResponse;
	}

	@Override
	public MattressResponse uploadImage(MultipartFile file, Long mattressId) throws IOException {
		Mattress mattress = mattressRepository.findById(mattressId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.MATTRESS_NOT_FOUND+mattressId));
		
		Image image;
		if(mattress.getImage()!=null) {
			image = mattress.getImage();
			if (!file.isEmpty()) {
				FileUploadUtils.saveMattressImage(file, mattressId);
				image.setTitle(mattressId.toString() + ".png");
				image.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/"+mattressId+".png");
				imageRepository.save(image);
				mattress.setImage(image);
				mattressRepository.save(mattress);
			}
		}else {
			image = new Image();
			if (!file.isEmpty()) {
				FileUploadUtils.saveMattressImage(file, mattressId);
				image.setTitle(mattressId.toString() + ".png");
				image.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/"+mattressId+".png");
				imageRepository.save(image);
				mattress.setImage(image);
				mattressRepository.save(mattress);
			}
		}
		
		
		MattressResponse mattressResponse = mapper.map(mattress, MattressResponse.class);
		return mattressResponse;
	}

	@Override
	public Image getImagesById(Long mattressId) {
		Mattress mattress = mattressRepository.findById(mattressId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.MATTRESS_NOT_FOUND+mattressId));
		
		return mattress.getImage();
	}

	@Override
	public List<MattressResponse> getTop3LatesMattresses() {
		List<Mattress> mattresses = mattressRepository.findTop3ByOrderByCreateAtDesc();
		List<MattressResponse> mattressResponses = 
				Arrays.asList(mapper.map(mattresses, MattressResponse[].class));
		return mattressResponses;
	}

	@Override
	public List<MattressResponse> getTop3BestSeller() {
		List<Mattress> mattresses = mattressRepository.findTop3ByOrderBySoldQuantityDesc();
		List<MattressResponse> mattressResponses = 
				Arrays.asList(mapper.map(mattresses, MattressResponse[].class));
		return mattressResponses;
	}

	@Override
	public List<MattressResponse> searchProducts(String query) {
		List<Mattress> mattresses = mattressRepository.searchProducts(query);
		List<MattressResponse> mattressResponses = 
				Arrays.asList(mapper.map(mattresses, MattressResponse[].class));
		return mattressResponses;
	}

}
