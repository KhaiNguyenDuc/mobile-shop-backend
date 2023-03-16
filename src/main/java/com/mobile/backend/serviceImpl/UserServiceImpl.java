package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.model.user.User;
import com.mobile.backend.payload.UserResponse;
import com.mobile.backend.repository.UserRepository;
import com.mobile.backend.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserResponse> userResponses = 
				Arrays.asList(modelMapper.map(users, UserResponse[].class));
		return userResponses;
	}

}
