package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {

	Size findByName(String string);


}
