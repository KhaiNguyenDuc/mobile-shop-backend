package com.mobile.backend;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.mobile.backend.model.Banner;
import com.mobile.backend.model.Brand;
import com.mobile.backend.model.Category;
import com.mobile.backend.model.Cloth;
import com.mobile.backend.model.Image;
import com.mobile.backend.model.Inventory;
import com.mobile.backend.model.Size;
import com.mobile.backend.model.cart.Cart;
import com.mobile.backend.model.cart.CartItem;
import com.mobile.backend.model.order.OrderItem;
import com.mobile.backend.model.order.OrderTrack;
import com.mobile.backend.model.user.Role;
import com.mobile.backend.model.user.RoleName;
import com.mobile.backend.model.user.User;
import com.mobile.backend.repository.BannerRepository;
import com.mobile.backend.repository.BrandRepository;
import com.mobile.backend.repository.CartItemRepository;
import com.mobile.backend.repository.CartRepository;
import com.mobile.backend.repository.CategoryRepository;
import com.mobile.backend.repository.ClothRepository;
import com.mobile.backend.repository.ImageRepository;
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
	PasswordEncoder encoder;
	
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
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	BannerRepository bannerRepository;

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
		roleRepository.save(roleUser);
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
		
		Image imageCasualWear = new Image();
		imageCasualWear.setTitle("1.png");
		imageCasualWear.setPath(AppConstant.UPLOAD_CATEGORY_DIRECTORY+"/1.png");
		imageRepository.save(imageCasualWear);
		
		Image image2 = new Image();
		image2.setTitle("2.png");
		image2.setPath(AppConstant.UPLOAD_CATEGORY_DIRECTORY+"/2.png");
		imageRepository.save(image2);
		
		Image image3 = new Image();
		image3.setTitle("3.png");
		image3.setPath(AppConstant.UPLOAD_CATEGORY_DIRECTORY+"/3.png");
		imageRepository.save(image3);
		
		Image image4 = new Image();
		image4.setTitle("4.png");
		image4.setPath(AppConstant.UPLOAD_CATEGORY_DIRECTORY+"/4.png");
		imageRepository.save(image4);
		
		Category casualWear = new Category();
		casualWear.setName("Casual Wear");
		casualWear.setImage(imageCasualWear);
		categoryRepository.save(casualWear);
		
		Category sportsWear = new Category();
		sportsWear.setName("Sports Wear");
		sportsWear.setImage(image2);
		categoryRepository.save(sportsWear);
		
		Category formalWear = new Category();
		formalWear.setName("Formal Wear");
		formalWear.setImage(image3);
		categoryRepository.save(formalWear);
		
		Category beachWear = new Category();
		beachWear.setName("Beach Wear");
		beachWear.setImage(image4);
		categoryRepository.save(beachWear);


	}
	
	@Order(5)
	@Test
	public void addCloth() {
		
		Brand brandGucci = brandRepository.findByName("Gucci").get();
		Brand brandLevis = brandRepository.findByName("Levis").get();
		Brand brandCalvinKlein = brandRepository.findByName("Calvin Klein").get();
		Brand brandAristino = brandRepository.findByName("Aristino").get();
		Brand brandMoiDien = brandRepository.findByName("Moi Dien").get();
		
		
		
		Category casualWear = categoryRepository.findByName("Casual Wear").get();
		Category sportsWear = categoryRepository.findByName("Sports Wear").get();
		Category formalWear = categoryRepository.findByName("Formal Wear").get();
		Category beachWear = categoryRepository.findByName("Beach Wear").get();
		
		
		Image image1 = new Image();
		image1.setTitle("1.png");
		image1.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/1.png");
		imageRepository.save(image1);
		
		Image image2 = new Image();
		image2.setTitle("2.png");
		image2.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/2.png");
		imageRepository.save(image2);

		Image image3 = new Image();
		image3.setTitle("3.png");
		image3.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/3.png");
		imageRepository.save(image3);

		Image image4 = new Image();
		image4.setTitle("4.png");
		image4.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/4.png");
		imageRepository.save(image4);

		Image image5 = new Image();
		image5.setTitle("5.png");
		image5.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/5.png");
		imageRepository.save(image5);

		Image image6 = new Image();
		image6.setTitle("6.png");
		image6.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/6.png");
		imageRepository.save(image6);

		Image image7 = new Image();
		image7.setTitle("7.png");
		image7.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/7.png");
		imageRepository.save(image7);

		Image image8 = new Image();
		image8.setTitle("8.png");
		image8.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/8.png");
		imageRepository.save(image8);

		Image image9 = new Image();
		image9.setTitle("9.png");
		image9.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/9.png");
		imageRepository.save(image9);

		Image image10 = new Image();
		image10.setTitle("10.png");
		image10.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/10.png");
		imageRepository.save(image10);

		Image image11 = new Image();
		image11.setTitle("11.png");
		image11.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/11.png");
		imageRepository.save(image11);

		Image image12 = new Image();
		image12.setTitle("12.png");
		image12.setPath(AppConstant.UPLOAD_CLOTH_DIRECTORY+"/12.png");
		imageRepository.save(image12);
		
		
		Cloth cloth1 = new Cloth();
		cloth1.setName("Bermuda Short");
		cloth1.setPrice(20.5);
		cloth1.setDescription("Awesome cloth");
		cloth1.setBrand(brandMoiDien);
		cloth1.setInventories(null);
		cloth1.setCategory(beachWear);
		cloth1.setImage(image1);
		clothRepository.save(cloth1);
		
		// Create and save a Cloth item for Casual Wear
		Cloth cloth2 = new Cloth();
		cloth2.setName("Jeans");
		cloth2.setPrice(60.0);
		cloth2.setDescription("Classic denim");
		cloth2.setBrand(brandLevis);
		cloth2.setInventories(null);
		cloth2.setImage(image2);
		cloth2.setCategory(casualWear);
		clothRepository.save(cloth2);

		// Create and save a Cloth item for Formal Wear
		Cloth cloth3 = new Cloth();
		cloth3.setName("Dress Shirt");
		cloth3.setPrice(45.0);
		cloth3.setDescription("Sharp and professional");
		cloth3.setBrand(brandCalvinKlein);
		cloth3.setInventories(null);
		cloth3.setImage(image3);
		cloth3.setCategory(formalWear);
		clothRepository.save(cloth3);

		// Create and save a Cloth item for Sports Wear
		Cloth cloth4 = new Cloth();
		cloth4.setName("Sweatpants");
		cloth4.setPrice(35.0);
		cloth4.setDescription("Comfortable for workouts");
		cloth4.setBrand(brandAristino);
		cloth4.setInventories(null);
		cloth4.setImage(image4);
		cloth4.setCategory(sportsWear);
		clothRepository.save(cloth4);

		// Create and save a Cloth item for Beach Wear
		Cloth cloth5 = new Cloth();
		cloth5.setName("Swim Shorts");
		cloth5.setPrice(25.0);
		cloth5.setDescription("Fun and colorful");
		cloth5.setBrand(brandMoiDien);
		cloth5.setInventories(null);
		cloth5.setImage(image5);
		cloth5.setCategory(beachWear);
		clothRepository.save(cloth5);

		// Create and save a Cloth item for Casual Wear
		Cloth cloth6 = new Cloth();
		cloth6.setName("Hoodie");
		cloth6.setPrice(50.0);
		cloth6.setDescription("Warm and cozy");
		cloth6.setBrand(brandLevis);
		cloth6.setInventories(null);
		cloth6.setImage(image6);
		cloth6.setCategory(casualWear);
		clothRepository.save(cloth6);

		// Create and save a Cloth item for Sports Wear
		Cloth cloth7 = new Cloth();
		cloth7.setName("Running Shorts");
		cloth7.setPrice(20.0);
		cloth7.setDescription("Lightweight and breathable");
		cloth7.setBrand(brandAristino);
		cloth7.setInventories(null);
		cloth7.setImage(image7);
		cloth7.setCategory(sportsWear);
		clothRepository.save(cloth7);

		// Create and save a Cloth item for Formal Wear
		Cloth cloth8 = new Cloth();
		cloth8.setName("Suit Jacket");
		cloth8.setPrice(120.0);
		cloth8.setDescription("Sharp and stylish");
		cloth8.setBrand(brandCalvinKlein);
		cloth8.setInventories(null);
		cloth8.setImage(image8);
		cloth8.setCategory(formalWear);
		clothRepository.save(cloth8);

		// Create and save a Cloth item for Beach Wear
		Cloth cloth9 = new Cloth();
		cloth9.setName("Sun Hat");
		cloth9.setPrice(15.0);
		cloth9.setDescription("Great for sun protection");
		cloth9.setBrand(brandMoiDien);
		cloth9.setInventories(null);
		cloth9.setImage(image9);
		cloth9.setCategory(beachWear);
		clothRepository.save(cloth9);

		// Create and save a Cloth item for Casual Wear
		Cloth cloth10 = new Cloth();
		cloth10.setName("T-Shirt");
		cloth10.setPrice(18.0);
		cloth10.setDescription("Simple and comfortable");
		cloth10.setBrand(brandLevis);
		cloth10.setInventories(null);
		cloth10.setImage(image10);
		cloth10.setCategory(casualWear);
		clothRepository.save(cloth10);
		
		Cloth cloth11 = new Cloth();
		cloth11.setName("Gucci T-Shirt");
		cloth11.setPrice(300.0);
		cloth11.setDescription("Comfortable and stylish t-shirt made by Gucci");
		cloth11.setBrand(brandGucci);
		cloth11.setImage(image11);
		cloth11.setInventories(null);
		cloth11.setCategory(casualWear);
		clothRepository.save(cloth11);

		Cloth cloth12 = new Cloth();
		cloth12.setName("Gucci Leather Jacket");
		cloth12.setPrice(1500.0);
		cloth12.setDescription("Luxurious leather jacket made by Gucci");
		cloth12.setBrand(brandGucci);
		cloth12.setImage(image12);
		cloth12.setInventories(null);
		cloth12.setCategory(formalWear);
		clothRepository.save(cloth12);
	}
	
	@Order(6)
	@Test
	public void addInventory() {
		Size sizeL = sizeRepository.findByName("L");
		Size sizeM = sizeRepository.findByName("M");
		Size sizeXL = sizeRepository.findByName("XL");
		Size sizeXXL = sizeRepository.findByName("XXL");
		Size sizeXXXL = sizeRepository.findByName("XXXL");
		
		// Get cloths
		Cloth cloth1 = clothRepository.findById(1L).get();
		Cloth cloth2 = clothRepository.findById(2L).get();
		Cloth cloth3 = clothRepository.findById(3L).get();
		Cloth cloth4 = clothRepository.findById(4L).get();
		Cloth cloth5 = clothRepository.findById(5L).get();
		Cloth cloth6 = clothRepository.findById(6L).get();
		Cloth cloth7 = clothRepository.findById(7L).get();
		Cloth cloth8 = clothRepository.findById(8L).get();
		Cloth cloth9 = clothRepository.findById(9L).get();
		Cloth cloth10 = clothRepository.findById(10L).get();
		Cloth cloth11 = clothRepository.findById(11L).get();
		Cloth cloth12 = clothRepository.findById(12L).get();
		// Create inventory 
		Inventory inventory1 = new Inventory();
		inventory1.setQuantity(10);
		inventory1.setCloth(cloth1);
		inventory1.setSize(sizeL);
		inventoryRepository.save(inventory1);
		
		// Create inventory items
		Inventory inventory2 = new Inventory();
		inventory2.setQuantity(5);
		inventory2.setCloth(cloth2);
		inventory2.setSize(sizeM);
		inventoryRepository.save(inventory2);

		Inventory inventory3 = new Inventory();
		inventory3.setQuantity(15);
		inventory3.setCloth(cloth3);
		inventory3.setSize(sizeXL);
		inventoryRepository.save(inventory3);

		Inventory inventory4 = new Inventory();
		inventory4.setQuantity(20);
		inventory4.setCloth(cloth4);
		inventory4.setSize(sizeL);
		inventoryRepository.save(inventory4);

		Inventory inventory5 = new Inventory();
		inventory5.setQuantity(8);
		inventory5.setCloth(cloth5);
		inventory5.setSize(sizeXXL);
		inventoryRepository.save(inventory5);

		Inventory inventory6 = new Inventory();
		inventory6.setQuantity(3);
		inventory6.setCloth(cloth6);
		inventory6.setSize(sizeXXXL);
		inventoryRepository.save(inventory6);
		
		Inventory inventory7 = new Inventory();
		inventory7.setQuantity(3);
		inventory7.setCloth(cloth1);
		inventory7.setSize(sizeXXXL);
		inventoryRepository.save(inventory7);
		
		Inventory inventory8 = new Inventory();
		inventory8.setQuantity(4);
		inventory8.setCloth(cloth8);
		inventory8.setSize(sizeXXXL);
		inventoryRepository.save(inventory8);
		
		Inventory inventory9 = new Inventory();
		inventory9.setQuantity(30);
		inventory9.setCloth(cloth9);
		inventory9.setSize(sizeXL);
		inventoryRepository.save(inventory9);
		
		Inventory inventory10 = new Inventory();
		inventory10.setQuantity(30);
		inventory10.setCloth(cloth10);
		inventory10.setSize(sizeXXXL);
		inventoryRepository.save(inventory10);
		
		Inventory inventory11 = new Inventory();
		inventory11.setQuantity(30);
		inventory11.setCloth(cloth11);
		inventory11.setSize(sizeL);
		inventoryRepository.save(inventory11);
		
		Inventory inventory12 = new Inventory();
		inventory12.setQuantity(30);
		inventory12.setCloth(cloth12);
		inventory12.setSize(sizeXL);
		inventoryRepository.save(inventory12);
		
		Inventory inventory13 = new Inventory();
		inventory13.setQuantity(15);
		inventory13.setCloth(cloth7);
		inventory13.setSize(sizeM);
		inventoryRepository.save(inventory12);

	}
	
	@Order(7)
	@Test
	public void addCart() {

		Cart cartKhai = new Cart();
		cartKhai.setUser(null);
		cartRepository.save(cartKhai);
		
		Cart cartKiet = new Cart();
		cartKiet.setUser(null);
		cartRepository.save(cartKiet);
	}
	
	@Order(8)
	@Test
	public void addCartItem() {
		
		// Get cloths
		Cloth cloth1 = clothRepository.findById(1L).get();
		Cloth cloth2 = clothRepository.findById(2L).get();
		Cloth cloth3 = clothRepository.findById(3L).get();

		
		// For user Khai
		Cart cartKhai = cartRepository.findById(1L).get();
		
		Size sizeL = sizeRepository.findByName("L");
		Size sizeM = sizeRepository.findByName("M");
		Size sizeXL = sizeRepository.findByName("XL");
		Size sizeXXL = sizeRepository.findByName("XXL");
		Size sizeXXXL = sizeRepository.findByName("XXXL");
		
		
		CartItem cartItem1 = new CartItem();
		cartItem1.setCloth(cloth1);
		cartItem1.setChoice_size(sizeXXXL);
		cartItem1.setCart(cartKhai);
		cartItem1.setQuantity(1);
		cartItemRepository.save(cartItem1);
		
		CartItem cartItem2 = new CartItem();
		cartItem2.setCloth(cloth2);
		cartItem2.setCart(cartKhai);
		cartItem2.setChoice_size(sizeM);
		cartItem2.setQuantity(2);
		cartItemRepository.save(cartItem2);
		
		CartItem cartItem3 = new CartItem();
		cartItem3.setCloth(cloth3);
		cartItem3.setCart(cartKhai);
		cartItem3.setChoice_size(sizeXL);
		cartItem3.setQuantity(3);
		cartItemRepository.save(cartItem3);
		

		
		
	}

	@Order(9)
	@Test
	public void addUsers() {
		
		Role roleUser = roleRepository.findByName(RoleName.USER);
		Role roleAdmin = roleRepository.findByName(RoleName.ADMIN);
		
		Cart cartKhai = cartRepository.findById(1L).get();
		Cart cartKiet = cartRepository.findById(2L).get();
		
		Image imageKhai = new Image();
		imageKhai.setTitle("1.png");
		imageKhai.setPath(AppConstant.UPLOAD_USER_DIRECTORY+"/1.png");
		imageRepository.save(imageKhai);
		
		Image imagekiet = new Image();
		imagekiet.setTitle("2.png");
		imagekiet.setPath(AppConstant.UPLOAD_USER_DIRECTORY+"/2.png");
		imageRepository.save(imagekiet);
		
		// normal user
		User userKhai = new User();
		userKhai.setUsername("khai");
		userKhai.setPassword(encoder.encode("123"));
		userKhai.setAddress("241, Nguyễn Trãi, Lái Thiêu, Thuận An, Bình Dương");
		userKhai.setEmail("duckhailinux@gmail.com");
		userKhai.setBirthday(LocalDate.of(2002,6,12));
		userKhai.setFirstName("khai");
		userKhai.setLastName("nguyen");
		userKhai.setPhoneNumber("0783511740");
		userKhai.setEnabled(Boolean.TRUE);
		userKhai.setRoles(Arrays.asList(roleUser));
		userKhai.setCart(cartKhai);
		userKhai.setImage(imageKhai);
		userRepository.save(userKhai);
		
		
		// user admin
		User userKiet = new User();
		userKiet.setUsername("admin");
		userKiet.setPassword(encoder.encode("1234"));
		userKiet.setAddress("Số 1 Võ Văn Ngân");
		userKiet.setEmail("admin@gmail.com");
		userKiet.setBirthday(LocalDate.of(1978,3,11));
		userKiet.setFirstName("admin");
		userKiet.setLastName("nguyen");
		userKiet.setPhoneNumber("0783521740");
		userKiet.setEnabled(Boolean.TRUE);
		userKiet.setCart(cartKiet);
		userKiet.setRoles(Arrays.asList(roleAdmin,roleUser));
		userKiet.setImage(imagekiet);
		userRepository.save(userKiet);
		
	}
	
	@Order(10)
	@Test
	public void addOrderTrack() {
		
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
	
	@Order(11)
	@Test
	public void addOrder() {
		
		User userKhai = userRepository.findById(1L).get();
		
		OrderTrack trackDelivering = orderTrackRepository.findByStatus(AppConstant.DELIVERING).get();
		
		com.mobile.backend.model.order.Order orderKhai = new com.mobile.backend.model.order.Order();
		orderKhai.setOrderDate(new Date());
		orderKhai.setDeliverCost(AppConstant.STANDARD_COST);
		orderKhai.setDeliverMethod(AppConstant.STANDARD);
		orderKhai.setOrderTrack(trackDelivering);
		orderKhai.setUser(userKhai);
		orderKhai.setOrderItems(null);
		orderRepository.save(orderKhai);
	}
	
	
	@Order(12)
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
	
	@Order(13)
	@Test
	public void addBanner() {
		
		Image image1 = new Image();
		image1.setTitle("1.png");
		image1.setPath(AppConstant.UPLOAD_BANNER_DIRECTORY+"/1.png");
		imageRepository.save(image1);
		
		Image image2 = new Image();
		image2.setTitle("2.png");
		image2.setPath(AppConstant.UPLOAD_BANNER_DIRECTORY+"/2.png");
		imageRepository.save(image2);
		
		Image image3 = new Image();
		image3.setTitle("3.png");
		image3.setPath(AppConstant.UPLOAD_BANNER_DIRECTORY+"/3.png");
		imageRepository.save(image3);
		
		Banner banner1 = new Banner();
		banner1.setImage(image1);
		bannerRepository.save(banner1);
		
		Banner banner2 = new Banner();
		banner2.setImage(image2);
		bannerRepository.save(banner2);
		
		Banner banner3 = new Banner();
		banner3.setImage(image3);
		bannerRepository.save(banner3);
	}
}
