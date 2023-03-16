package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.order.Order;

public interface OrderItemRepository extends JpaRepository<Order, Long>{

}
