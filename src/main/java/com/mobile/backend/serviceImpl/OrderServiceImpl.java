package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.order.Order;
import com.mobile.backend.model.user.User;
import com.mobile.backend.payload.OrderResponse;
import com.mobile.backend.repository.OrderRepository;
import com.mobile.backend.repository.UserRepository;
import com.mobile.backend.service.IOrderService;
import com.mobile.backend.untils.AppConstant;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public OrderResponse getOrderById(Long orderId) {
		
		// skip id so that modelmapping not map orderId with userId
				mapper.typeMap(OrderResponse.class, Order.class)
					.addMappings(mapper -> mapper.skip(Order::setId));
				
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.ORDER_NOT_FOUND+orderId));
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

	@Override
	public List<OrderResponse> getCurrentOrderByUserId(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+id));
		
		List<OrderResponse> orderResponses = 
				Arrays.asList(mapper.map(user.getOrders(), OrderResponse[].class));
		
		return orderResponses;
	}

}
