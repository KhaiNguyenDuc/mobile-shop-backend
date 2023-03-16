package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.mode.order.OrderTrack;

public interface OrderTrackRepository extends JpaRepository<OrderTrack, Long>{

}
