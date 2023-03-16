package com.mobile.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Category;
import com.mobile.backend.model.Cloth;


public interface ClothRepository extends JpaRepository<Cloth, Long> {

	List<Cloth> findByCategory(Category category);

	List<Cloth> findByBrand(Brand brand);

}
