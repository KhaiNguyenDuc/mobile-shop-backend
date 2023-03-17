package com.mobile.backend.service;

import java.util.List;

import com.mobile.backend.payload.OrderResponse;

public interface IOrderService {

	OrderResponse getOrderById(Long orderId);

	List<OrderResponse> getAllOrders();

	List<OrderResponse> getCurrentOrderByUserId(Long id);

}
