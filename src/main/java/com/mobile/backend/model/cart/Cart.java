package com.mobile.backend.model.cart;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobile.backend.model.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems;
	
	@OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	public void addCartItem(CartItem cartItem) {
		cartItems.add(cartItem);
	}
	
}
