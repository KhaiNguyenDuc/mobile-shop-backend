package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.user.Role;
import com.mobile.backend.model.user.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(RoleName name);

}
