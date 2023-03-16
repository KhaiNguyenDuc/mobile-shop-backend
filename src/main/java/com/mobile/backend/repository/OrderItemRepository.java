package com.mobile.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobile.backend.model.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
