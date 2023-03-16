package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.backend.model.cart.Cart;
import com.mobile.backend.payload.CartResponse;
import com.mobile.backend.repository.CartRepository;
import com.mobile.backend.service.ICartService;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public CartResponse getCartById(Long cartId) {

		// skip id so that modelmapping not map cartId with userId
		mapper.typeMap(CartResponse.class, Cart.class)
			.addMappings(mapper -> mapper.skip(Cart::setId));
		
		Cart cart = cartRepository.findById(cartId).get();
		CartResponse cartResponse = mapper.map(cart, CartResponse.class);
		
		return cartResponse;
	}

	@Override
	public List<CartResponse> getAllCart() {
		List<Cart> carts = cartRepository.findAll();
		List<CartResponse> cartResponses = 
				Arrays.asList(mapper.map(carts, CartResponse[].class));
		return cartResponses;
	}

}
