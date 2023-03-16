package com.mobile.backend.service;

import com.mobile.backend.security.UserPrincipal;

public interface ICustomUserService {
	public UserPrincipal loadUserByUserId(Long userId);
}
