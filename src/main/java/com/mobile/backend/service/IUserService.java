package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.UserResponse;

public interface IUserService {

	List<UserResponse> getAllUsers();

}
