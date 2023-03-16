package com.mobile.backend.payload;

import java.util.Date;
import java.util.List;

import com.mobile.backend.model.order.OrderItem;
import com.mobile.backend.model.order.OrderTrack;

import lombok.Data;

@Data
public class OrderResponse {

	private Long id;

	private Date orderDate;

	private String deliverMethod;

	private Double deliverCost;

	private List<OrderItem> orderItems;

	private OrderTrack orderTrack;

	private Long userId;
}
