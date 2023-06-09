package com.mobile.backend.serviceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.backend.Exception.ResourceExistException;
import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.Exception.UserNotFoundException;
import com.mobile.backend.model.Image;
import com.mobile.backend.model.cart.Cart;
import com.mobile.backend.model.order.Order;
import com.mobile.backend.model.user.Role;
import com.mobile.backend.model.user.RoleName;
import com.mobile.backend.model.user.User;
import com.mobile.backend.payload.request.UserProfileRequest;
import com.mobile.backend.payload.request.UserRequest;
import com.mobile.backend.payload.request.UserStatusRequest;
import com.mobile.backend.payload.response.CartResponse;
import com.mobile.backend.payload.response.OrderResponse;
import com.mobile.backend.payload.response.UserProfileResponse;
import com.mobile.backend.payload.response.UserResponse;
import com.mobile.backend.repository.ImageRepository;
import com.mobile.backend.repository.RoleRepository;
import com.mobile.backend.repository.UserRepository;
import com.mobile.backend.security.UserPrincipal;
import com.mobile.backend.service.IUserService;
import com.mobile.backend.untils.AppConstant;
import com.mobile.backend.untils.FileUploadUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	PasswordEncoder encoder;

	
	@Override
	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserResponse> userResponses = 
				Arrays.asList(mapper.map(users, UserResponse[].class));
		return userResponses;
	}

	@Override
	public UserResponse getUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+userId));
		UserResponse userResponse = mapper.map(user, UserResponse.class);
		return userResponse;
	}

	@Override
	public CartResponse getCartByUserId(Long userId) {
		// skip id so that modelmapping not map cartId with userId
				mapper.typeMap(CartResponse.class, Cart.class)
					.addMappings(mapper -> mapper.skip(Cart::setId));
				
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+userId));;
		CartResponse cartResponse = mapper.map(user.getCart(), CartResponse.class);
		return cartResponse;
	}

	@Override
	public List<OrderResponse> getOrdersByUserId(Long userId) {
		// skip id so that modelmapping not map cartId with userId
		mapper.typeMap(OrderResponse.class, Order.class)
			.addMappings(mapper -> mapper.skip(Order::setId));
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+userId));
		List<OrderResponse> orderResponses = 
				Arrays.asList(mapper.map(user.getOrders(), OrderResponse[].class));
		return orderResponses;
	}

	@Override
	public UserProfileResponse createUser(UserRequest userRequest) {
		String username = userRequest.getUsername();
		if(userRepository.existsByUsername(username)) {
			throw new ResourceExistException(AppConstant.USER_EXIST);
		}
		
		Role role = roleRepository.findByName(RoleName.USER);
		if(Objects.isNull(role)) {
			throw new ResourceNotFoundException(AppConstant.ROLE_NOT_FOUND + RoleName.USER);
		}
		
		User user = mapper.map(userRequest, User.class);
		user.setRoles(Arrays.asList(role));
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		
		return mapper.map(user, UserProfileResponse.class);
	}

	@Override
	public UserProfileResponse getCurrentUser(UserPrincipal userPrincipal) {
		String username = userPrincipal.getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(
						AppConstant.USER_NOT_FOUND + username));
		
		
		UserProfileResponse userProfile = new UserProfileResponse();
		userProfile.setId(user.getId());
		userProfile.setUsername(username);
		userProfile.setFirstName(user.getFirstName());
		userProfile.setLastName(user.getLastName());
		userProfile.setEmail(user.getEmail());
		userProfile.setPhoneNumber(user.getPhoneNumber());
		userProfile.setImage(user.getImage());
		userProfile.setAddress(user.getAddress());
		userProfile.setBirthday(user.getBirthday());
		return userProfile;
	}

	@Override
	public Boolean checkUsernameUnique(String username) {
		return !userRepository.existsByUsername(username);
	}

	@Override
	public Boolean checkEmailUnique(String email) {
		return !userRepository.existsByEmail(email);
	}

	@Override
	public CartResponse getCartByCurrentUser(UserPrincipal userPrincipal) {
		String username = userPrincipal.getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(
						AppConstant.USER_NOT_FOUND + username));
		
		CartResponse cartResponse = mapper.map(user.getCart(), CartResponse.class);
		return cartResponse;
	}

	@Override
	public List<OrderResponse> getOrdersByCurrentUser(UserPrincipal userPrincipal) {
		String username = userPrincipal.getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(
						AppConstant.USER_NOT_FOUND + username));
		
		List<Order> orders = user.getOrders();
		List<OrderResponse> orderResponses = 
				Arrays.asList(mapper.map(orders, OrderResponse[].class));
		return orderResponses;
	}

	@Override
	public UserProfileResponse updateCurrentProfile(UserPrincipal userPrincipal,
			UserProfileRequest userProfileRequest) {
		User user = userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+userPrincipal.getId()));
		
		User savedUser = updateProfile(user, userProfileRequest);
		UserProfileResponse userResponse = mapper.map(savedUser, UserProfileResponse.class );
		return userResponse;
	}
	
	public User updateProfile(User user, UserProfileRequest userProfileRequest) {
		
		user.setFirstName(userProfileRequest.getFirstName());
		user.setLastName(userProfileRequest.getLastName());
		user.setAddress(userProfileRequest.getAddress());
		user.setBirthday(userProfileRequest.getBirthday());
		user.setPhoneNumber(userProfileRequest.getPhoneNumber());
		return userRepository.save(user) ;
	}

	@Override
	public UserProfileResponse uploadImage(MultipartFile file, UserPrincipal userPrincipal) throws IOException {
		Long userID = userPrincipal.getId();
		
		User user = userRepository.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+userID));
		
		Image image;
		if(user.getImage()!=null) {
			image = user.getImage();
			if (!file.isEmpty()) {
				FileUploadUtils.saveUserImage(file, userID);
				image.setTitle(userPrincipal.getId() + ".png");
				image.setPath(AppConstant.UPLOAD_USER_DIRECTORY+"/"+userID+".png");
				imageRepository.save(image);
				user.setImage(image);
				userRepository.save(user);
			}
		}else {
			image = new Image();
			if (!file.isEmpty()) {
				FileUploadUtils.saveUserImage(file, userID);
				image.setTitle(userPrincipal.getId() + ".png");
				image.setPath(AppConstant.UPLOAD_USER_DIRECTORY+"/"+userID+".png");
				imageRepository.save(image);
				user.setImage(image);
				userRepository.save(user);
			}
		}
		
		
		
		UserProfileResponse userResponse = mapper.map(user, UserProfileResponse.class );
		return userResponse;
	}

	@Override
	public Image getImagesById(UserPrincipal userPrincipal) {
		User user = userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+userPrincipal.getId()));
		
		return user.getImage();
	}

	@Override
	public UserResponse updateUserStatus(UserStatusRequest userStatusRequest) {
		User user = userRepository.findById(userStatusRequest.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND + userStatusRequest.getUserId()));
		
		user.setEnabled(userStatusRequest.getIsEnabled());
		userRepository.save(user);
		
		return mapper.map(user, UserResponse.class);
	}

	@Override
	public Image getImagesById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+userId));
		
		return user.getImage();
	}

	
}
