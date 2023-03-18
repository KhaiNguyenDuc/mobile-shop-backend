package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {

}
