package com.mobile.backend.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobile.backend.model.Cloth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity; 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	@JsonIgnore
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "cloth_id", referencedColumnName = "id")
	private Cloth cloth;

	public Double getTotalPrice() {
		return quantity * cloth.getPrice();
	}

}
