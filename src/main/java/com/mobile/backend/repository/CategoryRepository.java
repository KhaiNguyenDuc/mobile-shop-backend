package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String string);

}
