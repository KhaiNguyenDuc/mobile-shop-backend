package com.mobile.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobile.backend.Exception.UserNotFoundException;
import com.mobile.backend.model.user.User;
import com.mobile.backend.repository.UserRepository;
import com.mobile.backend.service.ICustomUserService;
import com.mobile.backend.untils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserServiceImpl implements UserDetailsService, ICustomUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(
						AppConstant.USER_NOT_FOUND + username));
		return UserPrincipal.create(user);

	}

	@Override
	public UserPrincipal loadUserByUserId(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(
						AppConstant.USER_NOT_FOUND + "id" + userId));
		return UserPrincipal.create(user);
	}



}
