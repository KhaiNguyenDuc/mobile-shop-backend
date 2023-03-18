package com.mobile.backend.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mobile.backend.Exception.ResourceNotFoundException;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Size;
import com.mobile.backend.model.cart.Cart;
import com.mobile.backend.model.cart.CartItem;
import com.mobile.backend.model.user.User;
import com.mobile.backend.payload.request.CartItemRequest;
import com.mobile.backend.payload.response.ApiResponse;
import com.mobile.backend.payload.response.CartResponse;
import com.mobile.backend.repository.CartItemRepository;
import com.mobile.backend.repository.CartRepository;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.repository.SizeRepository;
import com.mobile.backend.repository.UserRepository;
import com.mobile.backend.service.ICartService;
import com.mobile.backend.untils.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements ICartService {

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClothRepository clothRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	SizeRepository sizeRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public CartResponse getCartById(Long cartId) {

		// skip id so that modelmapping not map cartId with userId
		mapper.typeMap(CartResponse.class, Cart.class)
			.addMappings(mapper -> mapper.skip(Cart::setId));
		
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CART_NOT_FOUND+cartId));
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

	@Override
	public CartResponse getCurrentCartByUserId(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+userId));
		
		CartResponse cartResponse = mapper.map(user.getCart(), CartResponse.class);
		
		return cartResponse;
	}

	@Override
	public CartResponse addCartItemByUserId(Long id, CartItemRequest itemRequest) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+id));
		
		Cart cart = user.getCart();
		
		// get cloth
		Long clothId = itemRequest.getClothId();
		Cloth cloth = clothRepository.findById(clothId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.CLOTH_NOT_FOUND+clothId));
		
		CartItem cartItemOld = checkClothExist(cart, cloth);
		
		if(Objects.nonNull(cartItemOld)) {
			
			// increase quantity
			cartItemOld.setQuantity(cartItemOld.getQuantity() + itemRequest.getQuantity());
			cartItemRepository.save(cartItemOld);
		}else {
			
			Size size = sizeRepository.findById(itemRequest.getChoice_sizeId())
					.orElseThrow(() -> new ResourceNotFoundException(AppConstant.SIZE_NOT_FOUND+itemRequest.getChoice_sizeId()));
			
			// create cartItem
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(itemRequest.getQuantity());
			cartItem.setChoice_size(size);
			cartItem.setCloth(cloth);
			cartItem.setCart(cart);
			cartItemRepository.save(cartItem);
			
			// add again so that cartResponse will be updated
			cart.addCartItem(cartItem);
			cartRepository.save(cart);
		}
		

		CartResponse cartResponse = mapper.map(cart, CartResponse.class);
		
		return cartResponse;
	}
	
	public CartItem checkClothExist(Cart cart, Cloth cloth) {
		
		for (CartItem cartItem : cart.getCartItems()) {
			if(cartItem.getCloth().getId().equals(cloth.getId())) {
				return cartItem;
			}
		}
		
		return null;
	}

	@Override
	public ApiResponse deleteCartItemById(Long id, Long cartItemId) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+id));
		
		Boolean flag = false;
		
		for (CartItem item : user.getCart().getCartItems()) {
			if(item.getId().equals(cartItemId)) {
				cartItemRepository.delete(item);
				flag=true;
			}
		}
		if(flag==true) {
			return new ApiResponse(Boolean.TRUE,AppConstant.DELETE_SUCCESS,HttpStatus.OK);
		}else {
			return new ApiResponse(Boolean.FALSE,AppConstant.DELETE_FAILURE,HttpStatus.OK);
		}
		
	}

	@Override
	public CartResponse updateCartItemById(Long id, Long cartItemId, CartItemRequest itemRequest) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_ID_NOT_FOUND+id));
		
		Size size = sizeRepository.findById(itemRequest.getChoice_sizeId())
				.orElseThrow(() -> new ResourceNotFoundException(AppConstant.SIZE_NOT_FOUND+itemRequest.getChoice_sizeId()));
		
		Boolean flag = false;
		
		for (CartItem item : user.getCart().getCartItems()) {
			if(item.getId().equals(cartItemId)) {
				item.setQuantity(itemRequest.getQuantity());
				item.setChoice_size(size);
				cartItemRepository.save(item);
				flag = true;
			}
		}
		
		if(flag.equals(false)) {
			throw new ResourceNotFoundException(AppConstant.CART_ITEM_NOT_FOUND_USER+id);
		}

		CartResponse cartResponse = mapper.map(user.getCart(), CartResponse.class);
		
		return cartResponse;
	}

}
