package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Brand findByName(String string);

}
