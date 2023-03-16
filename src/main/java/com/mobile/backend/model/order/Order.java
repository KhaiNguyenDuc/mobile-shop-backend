package com.mobile.backend.model.order;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobile.backend.model.user.User;
import com.mobile.backend.untils.AppConstant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "order_date", nullable = false)
	private Date orderDate;
	
	
	@Column(name = "deliver_method")
	private String deliverMethod;
	
	@Column(name = "deliver_cost")
	private Double deliverCost;
	
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;	
	
	@ManyToOne
	@JoinColumn(name = "order_track_id", referencedColumnName = "id", nullable = false)
	private OrderTrack orderTrack;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonIgnore
	private User user;
	
	public Double getTotalProductPrice() {
		Double total = 0.0;
		for (OrderItem orderItem : orderItems) {
			total += orderItem.getTotalPrice();
		}
		return total;
	}
	
	public Double getTotalPrice() {
		Double total = getTotalProductPrice();
		if(deliverMethod.equals(AppConstant.STANDARD)) {
			total = total + AppConstant.STANDARD_COST;
		}else if(deliverMethod.equals(AppConstant.FAST)) {
			total = total + AppConstant.FAST_COST;
		}else if(deliverMethod.equals(AppConstant.VERY_FAST)) {
			total = total + AppConstant.VERY_FAST_COST;
		}
		return total;
	}
	
	public int getNumProduct() {
		int total = 0;
		for (OrderItem orderItem : orderItems) {
			total += orderItem.getQuantity();
		}
		return total;
	}
}
