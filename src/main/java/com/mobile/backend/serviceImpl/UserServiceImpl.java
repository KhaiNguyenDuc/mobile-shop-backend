package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobile.backend.Exception.ResourceExistException;
import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.cart.Cart;
import com.mobile.backend.model.order.Order;
import com.mobile.backend.model.user.Role;
import com.mobile.backend.model.user.RoleName;
import com.mobile.backend.model.user.User;
import com.mobile.backend.payload.CartResponse;
import com.mobile.backend.payload.OrderResponse;
import com.mobile.backend.payload.UserProfileResponse;
import com.mobile.backend.payload.UserRequest;
import com.mobile.backend.payload.UserResponse;
import com.mobile.backend.repository.RoleRepository;
import com.mobile.backend.repository.UserRepository;
import com.mobile.backend.service.IUserService;
import com.mobile.backend.untils.AppConstant;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
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

}
