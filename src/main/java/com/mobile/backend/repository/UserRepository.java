package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
