package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Cloth;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	Brand findByName(String string);

	Brand findByClothes(Cloth cloth);

}
