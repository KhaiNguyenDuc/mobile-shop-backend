package com.mobile.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobile.backend.model.cart.CartItem;
import com.mobile.backend.model.order.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clothes")
public class Cloth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="description")
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name="brand_id", referencedColumnName = "id")
	private Brand brand;
	
	@OneToMany(mappedBy = "cloth")
	private List<Inventory> inventory;
	
	@ManyToOne
	@JoinColumn(name="category_id",referencedColumnName = "id")
	private Category category;
	
	@OneToMany(mappedBy = "cloth")
	@JsonIgnore
	private List<CartItem> cart;
	
	@OneToMany(mappedBy = "cloth")
	@JsonIgnore
	private List<OrderItem> orderItems;
	
}
