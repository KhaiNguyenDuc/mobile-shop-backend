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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany
	@JoinTable(
			name="cloth_size",
			joinColumns = @JoinColumn(name="cloth_id"),
			inverseJoinColumns = @JoinColumn(name="size_id"))
	private List<Size> size;
	
	@ManyToOne
	@JoinColumn(name="brand_id", referencedColumnName = "id")
	private Brand brand;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inventory_id", referencedColumnName = "id")
	private Inventory inventory;
	
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
