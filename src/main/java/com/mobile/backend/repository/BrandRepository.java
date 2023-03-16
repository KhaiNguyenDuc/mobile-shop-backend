package com.mobile.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Cloth;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Optional<Brand> findByName(String string);

	Optional<Brand> findByClothes(Cloth cloth);

}
