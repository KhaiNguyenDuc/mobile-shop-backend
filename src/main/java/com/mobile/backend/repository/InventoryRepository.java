package com.mobile.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Inventory;
import com.mobile.backend.model.Size;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Size> findSizeByCloth(Cloth cloth);

}
