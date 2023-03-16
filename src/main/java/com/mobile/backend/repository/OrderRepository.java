package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.mode.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
