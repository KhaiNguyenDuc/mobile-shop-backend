package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
