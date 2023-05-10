package com.mobile.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Mattress;
import com.mobile.backend.model.Inventory;
import com.mobile.backend.model.Size;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Size> findSizeByMattress(Mattress mattress);

	Optional<Inventory> findByMattressAndSize(Mattress mattress, Size choice_size);

}
