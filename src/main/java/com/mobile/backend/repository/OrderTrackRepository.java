package com.mobile.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.order.OrderTrack;

public interface OrderTrackRepository extends JpaRepository<OrderTrack, Long>{

	Optional<OrderTrack> findByStatus(String delivering);

}
