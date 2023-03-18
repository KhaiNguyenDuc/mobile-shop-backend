package com.mobile.backend.payload.response;

import java.util.Date;
import java.util.List;

import com.mobile.backend.model.cart.Cart;
import com.mobile.backend.model.order.Order;
import com.mobile.backend.model.user.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Boolean enabled;
	private Date birthday;
	private String address;
	private Cart cart;
	private List<Order> orders;
	private List<Role> roles;
	private String image;


	
}
