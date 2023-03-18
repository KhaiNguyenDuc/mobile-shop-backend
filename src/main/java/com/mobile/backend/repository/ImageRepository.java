package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
