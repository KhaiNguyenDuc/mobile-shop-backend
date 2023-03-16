package com.mobile.backend;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Category;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Inventory;
import com.mobile.backend.model.Size;
import com.mobile.backend.model.cart.Cart;
import com.mobile.backend.model.cart.CartItem;
import com.mobile.backend.model.order.OrderItem;
import com.mobile.backend.model.order.OrderTrack;
import com.mobile.backend.model.user.Role;
import com.mobile.backend.model.user.RoleName;
import com.mobile.backend.model.user.User;
import com.mobile.backend.repository.BrandRepository;
import com.mobile.backend.repository.CartItemRepository;
import com.mobile.backend.repository.CartRepository;
import com.mobile.backend.repository.CategoryRepository;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.repository.InventoryRepository;
import com.mobile.backend.repository.OrderItemRepository;
import com.mobile.backend.repository.OrderRepository;
import com.mobile.backend.repository.OrderTrackRepository;
import com.mobile.backend.repository.RoleRepository;
import com.mobile.backend.repository.SizeRepository;
import com.mobile.backend.repository.UserRepository;
import com.mobile.backend.untils.AppConstant;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
public class InsertData {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	ClothRepository clothRepository;
	
	@Autowired
	SizeRepository sizeRepository;
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	OrderTrackRepository orderTrackRepository;

	@Test
	@Order(1)
	public void testCreateRole() {
		// Create role admin
		Role roleAdmin = new Role();
		roleAdmin.setName(RoleName.ADMIN);
		roleRepository.save(roleAdmin);

		// Create role user
		Role roleUser = new Role();
		roleUser.setName(RoleName.USER);
	
	}
	
	
	@Order(2)
	@Test
	public void addBrand() {
		
		// Create and save a brand object for Gucci
		Brand brandGucci = new Brand();
		brandGucci.setName("Gucci");
		brandRepository.save(brandGucci);
		
		// Create and save a brand object for Levi's
		Brand brandLevis = new Brand();
		brandLevis.setName("Levis");
		brandRepository.save(brandLevis);

		// Create and save a brand object for Calvin Klein
		Brand brandCalvinKlein = new Brand();
		brandCalvinKlein.setName("Calvin Klein");
		brandRepository.save(brandCalvinKlein);

		// Create and save a brand object for Ralph Lauren
		Brand brandAristino = new Brand();
		brandAristino.setName("Aristino");
		brandRepository.save(brandAristino);
		
		// Create and save a brand object for Biti's
		Brand brandMoiDien = new Brand();
		brandMoiDien.setName("Moi Dien");
		brandRepository.save(brandMoiDien);
	}
	
	@Order(3)
	@Test
	public void addSize() {
		Size sizeM = new Size();
		sizeM.setName("M");
		sizeRepository.save(sizeM);
		
		Size sizeL = new Size();
		sizeL.setName("L");
		sizeRepository.save(sizeL);
		
		Size sizeXL = new Size();
		sizeXL.setName("XL");
		sizeRepository.save(sizeXL);
		
		Size sizeXXL = new Size();
		sizeXXL.setName("XXL");
		sizeRepository.save(sizeXXL);
		
		Size sizeXXXL = new Size();
		sizeXXXL.setName("XXXL");
		sizeRepository.save(sizeXXXL);
		
	}
	
	@Order(4)
	@Test
	public void addCategory() {
		
		Category casualWear = new Category();
		casualWear.setName("Casual Wear");
		categoryRepository.save(casualWear);
		
		Category sportsWear = new Category();
		sportsWear.setName("Sports Wear");
		categoryRepository.save(sportsWear);
		
		Category formalWear = new Category();
		formalWear.setName("Formal Wear");
		categoryRepository.save(formalWear);
		
		Category beachWear = new Category();
		beachWear.setName("Beach Wear");
		categoryRepository.save(beachWear);

		
	}
	
	@Order(5)
	@Test
	public void addCloth() {
		
		Brand brandGucci = brandRepository.findByName("Gucci");
		Brand brandLevis = brandRepository.findByName("Levis");
		Brand brandCalvinKlein = brandRepository.findByName("Calvin Klein");
		Brand brandAristino = brandRepository.findByName("Aristino");
		Brand brandMoiDien = brandRepository.findByName("Moi Dien");
		
		Size sizeL = sizeRepository.findByName("L");
		Size sizeM = sizeRepository.findByName("M");
		Size sizeXL = sizeRepository.findByName("XL");
		Size sizeXXL = sizeRepository.findByName("XXL");
		Size sizeXXXL = sizeRepository.findByName("XXXL");
		
		Category casualWear = categoryRepository.findByName("Casual Wear");
		Category sportsWear = categoryRepository.findByName("Sport Wear");
		Category formalWear = categoryRepository.findByName("Formal Wear");
		Category beachWear = categoryRepository.findByName("Beach Wear");
		
		
		Cloth cloth1 = new Cloth();
		cloth1.setName("Bermuda Short");
		cloth1.setPrice(20.5);
		cloth1.setDescription("Awesome cloth");
		cloth1.setSize(List.of(sizeL,sizeM,sizeXL,sizeXXL,sizeXXXL));
		cloth1.setBrand(brandMoiDien);
		cloth1.setInventory(new Inventory(10));
		cloth1.setCategory(beachWear);
		clothRepository.save(cloth1);
		
		// Create and save a Cloth item for Casual Wear
		Cloth cloth2 = new Cloth();
		cloth2.setName("Jeans");
		cloth2.setPrice(60.0);
		cloth2.setDescription("Classic denim");
		cloth2.setSize(List.of(sizeM, sizeL, sizeXL, sizeXXL));
		cloth2.setBrand(brandLevis);
		cloth2.setInventory(new Inventory(20));
		cloth2.setCategory(casualWear);
		clothRepository.save(cloth2);

		// Create and save a Cloth item for Formal Wear
		Cloth cloth3 = new Cloth();
		cloth3.setName("Dress Shirt");
		cloth3.setPrice(45.0);
		cloth3.setDescription("Sharp and professional");
		cloth3.setSize(List.of(sizeM, sizeL, sizeXL));
		cloth3.setBrand(brandCalvinKlein);
		cloth3.setInventory(new Inventory(15));
		cloth3.setCategory(formalWear);
		clothRepository.save(cloth3);

		// Create and save a Cloth item for Sports Wear
		Cloth cloth4 = new Cloth();
		cloth4.setName("Sweatpants");
		cloth4.setPrice(35.0);
		cloth4.setDescription("Comfortable for workouts");
		cloth4.setSize(List.of(sizeM, sizeL, sizeXL));
		cloth4.setBrand(brandAristino);
		cloth4.setInventory(new Inventory(25));
		cloth4.setCategory(sportsWear);
		clothRepository.save(cloth4);

		// Create and save a Cloth item for Beach Wear
		Cloth cloth5 = new Cloth();
		cloth5.setName("Swim Shorts");
		cloth5.setPrice(25.0);
		cloth5.setDescription("Fun and colorful");
		cloth5.setSize(List.of(sizeM, sizeL, sizeXL, sizeXXL));
		cloth5.setBrand(brandMoiDien);
		cloth5.setInventory(new Inventory(12));
		cloth5.setCategory(beachWear);
		clothRepository.save(cloth5);

		// Create and save a Cloth item for Casual Wear
		Cloth cloth6 = new Cloth();
		cloth6.setName("Hoodie");
		cloth6.setPrice(50.0);
		cloth6.setDescription("Warm and cozy");
		cloth6.setSize(List.of(sizeM, sizeL, sizeXL, sizeXXL));
		cloth6.setBrand(brandLevis);
		cloth6.setInventory(new Inventory(18));
		cloth6.setCategory(casualWear);
		clothRepository.save(cloth6);

		// Create and save a Cloth item for Sports Wear
		Cloth cloth7 = new Cloth();
		cloth7.setName("Running Shorts");
		cloth7.setPrice(20.0);
		cloth7.setDescription("Lightweight and breathable");
		cloth7.setSize(List.of(sizeM, sizeL, sizeXL));
		cloth7.setBrand(brandAristino);
		cloth7.setInventory(new Inventory(30));
		cloth7.setCategory(sportsWear);
		clothRepository.save(cloth7);

		// Create and save a Cloth item for Formal Wear
		Cloth cloth8 = new Cloth();
		cloth8.setName("Suit Jacket");
		cloth8.setPrice(120.0);
		cloth8.setDescription("Sharp and stylish");
		cloth8.setSize(List.of(sizeM, sizeL, sizeXL));
		cloth8.setBrand(brandCalvinKlein);
		cloth8.setInventory(new Inventory(8));
		cloth8.setCategory(formalWear);
		clothRepository.save(cloth8);

		// Create and save a Cloth item for Beach Wear
		Cloth cloth9 = new Cloth();
		cloth9.setName("Sun Hat");
		cloth9.setPrice(15.0);
		cloth9.setDescription("Great for sun protection");
		cloth9.setSize(List.of(sizeM, sizeL));
		cloth9.setBrand(brandMoiDien);
		cloth9.setInventory(new Inventory(25));
		cloth9.setCategory(beachWear);
		clothRepository.save(cloth9);

		// Create and save a Cloth item for Casual Wear
		Cloth cloth10 = new Cloth();
		cloth10.setName("T-Shirt");
		cloth10.setPrice(18.0);
		cloth10.setDescription("Simple and comfortable");
		cloth10.setSize(List.of(sizeM, sizeL, sizeXL, sizeXXL));
		cloth10.setBrand(brandLevis);
		cloth10.setInventory(new Inventory(22));
		cloth10.setCategory(casualWear);
		clothRepository.save(cloth10);
		
		Cloth cloth11 = new Cloth();
		cloth11.setName("Gucci T-Shirt");
		cloth11.setPrice(300.0);
		cloth11.setDescription("Comfortable and stylish t-shirt made by Gucci");
		cloth11.setSize(List.of(sizeM, sizeL, sizeXL));
		cloth11.setBrand(brandGucci);
		cloth11.setInventory(new Inventory(20));
		cloth11.setCategory(casualWear);
		clothRepository.save(cloth11);

		Cloth cloth12 = new Cloth();
		cloth12.setName("Gucci Leather Jacket");
		cloth12.setPrice(1500.0);
		cloth12.setDescription("Luxurious leather jacket made by Gucci");
		cloth12.setSize(List.of(sizeL, sizeXL, sizeXXL));
		cloth12.setBrand(brandGucci);
		cloth12.setInventory(new Inventory(5));
		cloth12.setCategory(formalWear);
		clothRepository.save(cloth12);
	}
	
	@Order(6)
	@Test
	public void addCart() {

		Cart cartKhai = new Cart();
		cartKhai.setUser(null);
		cartRepository.save(cartKhai);
	}
	
	@Order(7)
	@Test
	public void addCartItem() {
		
		// Get cloths
		Cloth cloth1 = clothRepository.findById(1L).get();
		Cloth cloth2 = clothRepository.findById(2L).get();
		Cloth cloth3 = clothRepository.findById(3L).get();
		Cloth cloth4 = clothRepository.findById(4L).get();
		Cloth cloth5 = clothRepository.findById(5L).get();
		Cloth cloth6 = clothRepository.findById(6L).get();
		Cloth cloth7 = clothRepository.findById(7L).get();
		// For user Khai
		Cart cartKhai = cartRepository.findById(1L).get();
		
		CartItem cartItem1 = new CartItem();
		cartItem1.setCloth(cloth1);
		cartItem1.setCart(cartKhai);
		cartItem1.setQuantity(1);
		cartItemRepository.save(cartItem1);
		
		CartItem cartItem2 = new CartItem();
		cartItem2.setCloth(cloth2);
		cartItem2.setCart(cartKhai);
		cartItem2.setQuantity(2);
		cartItemRepository.save(cartItem2);
		
		CartItem cartItem3 = new CartItem();
		cartItem3.setCloth(cloth3);
		cartItem3.setCart(cartKhai);
		cartItem3.setQuantity(3);
		cartItemRepository.save(cartItem3);
	}
	

	
	@Order(8)
	@Test
	public void addUsers() {
		
		Role roleAdmin = roleRepository.findByName(RoleName.ADMIN);
		Role roleUser = roleRepository.findByName(RoleName.USER);
		
		Cart cartKhai = cartRepository.findById(1L).get();
		
		
		User userKhai = new User();
		userKhai.setUsername("admin");
		userKhai.setPassword("123");
		userKhai.setAddress("241, Nguyễn Trãi, Lái Thiêu, Thuận An, Bình Dương");
		userKhai.setEmail("duckhailinux@gmail.com");
		userKhai.setBirthday(LocalDate.of(2002,6,12));
		userKhai.setFirstName("khai");
		userKhai.setLastName("nguyen");
		userKhai.setPhoneNumber("0783511740");
		userKhai.setEnabled(Boolean.TRUE);
		userKhai.setRoles(Arrays.asList(roleUser));
		userKhai.setCart(cartKhai);
		userRepository.save(userKhai);
		
	}
	
	@Order(9)
	@Test
	public void addOrderTrack() {
		
		// Payment process
		OrderTrack trackPaymentProcess = new OrderTrack();
		trackPaymentProcess.setStatus(AppConstant.PAYMENT_PROCESS);
		orderTrackRepository.save(trackPaymentProcess);
		
		// Delivery
		OrderTrack trackDelivering = new OrderTrack();
		trackDelivering.setStatus(AppConstant.DELIVERING);
		orderTrackRepository.save(trackDelivering);

		// Preparing
		OrderTrack trackPreparing = new OrderTrack();
		trackPreparing.setStatus(AppConstant.PREPARING);
		orderTrackRepository.save(trackPreparing);

		// Completed
		OrderTrack trackCompleted = new OrderTrack();
		trackCompleted.setStatus(AppConstant.COMPLETED);
		orderTrackRepository.save(trackCompleted);
	}
	
	@Order(10)
	@Test
	public void addOrder() {
		
		User userKhai = userRepository.findById(1L).get();
		
		OrderTrack trackDelivering = orderTrackRepository.findByStatus(AppConstant.DELIVERING);
		
		com.mobile.backend.model.order.Order orderKhai = new com.mobile.backend.model.order.Order();
		orderKhai.setOrderDate(new Date());
		orderKhai.setDeliverCost(AppConstant.STANDARD_COST);
		orderKhai.setDeliverMethod(AppConstant.STANDARD);
		orderKhai.setOrderTrack(trackDelivering);
		orderKhai.setUser(userKhai);
		orderKhai.setOrderItems(null);
		orderRepository.save(orderKhai);
	}
	
	
	@Order(11)
	@Test
	public void addOrderItem() {
		
		
		Cloth cloth1 = clothRepository.findById(1L).get();
		Cloth cloth2 = clothRepository.findById(2L).get();
		Cloth cloth3 = clothRepository.findById(3L).get();
		
		
		
		com.mobile.backend.model.order.Order orderKhai = orderRepository.findById(1L).get();
		
	
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setQuantity(2);
		orderItem1.setCloth(cloth1);
		orderItem1.setOrder(orderKhai);
		orderItemRepository.save(orderItem1);
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setQuantity(2);
		orderItem2.setCloth(cloth2);
		orderItem2.setOrder(orderKhai);
		orderItemRepository.save(orderItem2);
		
		OrderItem orderItem3 = new OrderItem();
		orderItem3.setQuantity(2);
		orderItem3.setCloth(cloth3);
		orderItem3.setOrder(orderKhai);
		orderItemRepository.save(orderItem3);
	}
}
