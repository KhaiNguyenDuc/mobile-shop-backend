package com.mobile.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Category;
import com.mobile.backend.model.Mattress;

public interface MattressRepository extends JpaRepository<Mattress, Long> {

	List<Mattress> findByCategory(Category category);

	List<Mattress> findByBrand(Brand brand);

	List<Mattress> findTop3ByOrderByCreateAtDesc();

	List<Mattress> findTop3ByOrderBySoldQuantityDesc();

	@Query("SELECT p FROM Mattress p WHERE " + "p.name LIKE CONCAT('%',:query, '%')")
	List<Mattress> searchProducts(String query);

}
