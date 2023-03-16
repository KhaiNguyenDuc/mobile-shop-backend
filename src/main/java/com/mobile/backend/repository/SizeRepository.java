package com.mobile.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {

	Size findByName(String string);

	List<Size> findByClothes(Cloth cloth);

}
