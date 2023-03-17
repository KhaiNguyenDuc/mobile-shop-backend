package com.mobile.backend.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Size;

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
@Table(name = "cart_item")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "cloth_id", referencedColumnName = "id")
	private Cloth cloth;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	@JsonIgnore
	private Cart cart;

	@ManyToOne
	@JoinColumn(name="size_id",referencedColumnName = "id")
	private Size choice_size;
	
	public Double getTotalPrice() {
		return quantity * cloth.getPrice();
	}
}
