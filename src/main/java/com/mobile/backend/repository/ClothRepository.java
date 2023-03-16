package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mobile.backend.model.Cloth;


public interface ClothRepository extends JpaRepository<Cloth, Long> {

}
