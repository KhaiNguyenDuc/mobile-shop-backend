package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Inventory;
import com.mobile.backend.model.cart.CartItem;
import com.mobile.backend.model.order.Order;
import com.mobile.backend.model.order.OrderItem;
import com.mobile.backend.model.order.OrderTrack;
import com.mobile.backend.model.user.User;
import com.mobile.backend.payload.request.OrderRequest;
import com.mobile.backend.payload.response.OrderResponse;
import com.mobile.backend.repository.CartItemRepository;
import com.mobile.backend.repository.InventoryRepository;
import com.mobile.backend.repository.OrderItemRepository;
import com.mobile.backend.repository.OrderRepository;
import com.mobile.backend.repository.OrderTrackRepository;
import com.mobile.backend.repository.UserRepository;
import com.mobile.backend.service.IOrderService;
import com.mobile.backend.untils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	@Autowired
	OrderTrackRepository orderTrackRepository;
	
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

	@Override
	public OrderResponse submitOrder(Long id, OrderRequest orderRequest) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+id));
		
		List<CartItem> cartItems = cartItemRepository.findByIdInAndCart(orderRequest.getCartItemId(),user.getCart());
		
		Order order = processOrder(user,cartItems,orderRequest);
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setId(order.getId());
		orderResponse.setDeliverMethod(order.getDeliverMethod());
		orderResponse.setDeliverCost(order.getDeliverCost());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setOrderTrack(order.getOrderTrack());
		orderResponse.setOrderItems(order.getOrderItems());
		orderResponse.setTotalPrice(order.getTotalPrice());
		orderResponse.setTotalProductPrice(order.getTotalProductPrice());
		orderResponse.setUserId(id);
		
		return orderResponse;
	}
	
	public Order processOrder(User user,List<CartItem> cartItems, OrderRequest orderRequest) {
		
		OrderTrack orderTrack = orderTrackRepository.findByStatus(AppConstant.PREPARING)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.ORDER_TRACK_NOT_FOUND+AppConstant.PREPARING));
		
		Order order = new Order();
		order.setUser(user);
		order.setDeliverMethod(orderRequest.getDeliverMethod());
		
		if(orderRequest.getDeliverMethod().equals(AppConstant.VERY_FAST)) {
			order.setDeliverCost(AppConstant.VERY_FAST_COST);
		}else if(orderRequest.getDeliverMethod().equals(AppConstant.FAST)) {
			order.setDeliverCost(AppConstant.FAST_COST);
		}else {
			order.setDeliverCost(AppConstant.STANDARD_COST);
		}
		
		order.setOrderDate(new Date());
		order.setOrderTrack(orderTrack);
		
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setMattress(cartItem.getMattress());
			
			OrderItem savedOItem =  orderItemRepository.save(orderItem);
			order.addOrderItems(savedOItem);
			
			Inventory inventory = inventoryRepository.findByMattressAndSize(cartItem.getMattress(),cartItem.getChoice_size())
					.orElseThrow(() -> new ResourceNotFoundException(AppConstant.INVENTORY_NOT_FOUND));
			inventory.setQuantity(inventory.getQuantity()-orderItem.getQuantity());
			inventoryRepository.save(inventory);
			cartItemRepository.delete(cartItem);
			
		}
		
		return orderRepository.save(order);
	}

}
