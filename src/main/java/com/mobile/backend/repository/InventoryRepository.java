package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
