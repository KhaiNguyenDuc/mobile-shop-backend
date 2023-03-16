package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.model.order.Order;
import com.mobile.backend.payload.OrderResponse;
import com.mobile.backend.repository.OrderRepository;
import com.mobile.backend.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public OrderResponse getOrderById(Long orderId) {
		
		// skip id so that modelmapping not map orderId with userId
				mapper.typeMap(OrderResponse.class, Order.class)
					.addMappings(mapper -> mapper.skip(Order::setId));
				
		Order order = orderRepository.findById(orderId).get();
		OrderResponse orderResponse = mapper.map(order, OrderResponse.class);
		return orderResponse;
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderResponse> orderResponses = 
				Arrays.asList(mapper.map(orders, OrderResponse[].class));
		return orderResponses;
	}

}
