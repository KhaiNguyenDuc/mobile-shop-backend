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
import com.mobile.backend.model.Image;
import com.mobile.backend.model.Inventory;
import com.mobile.backend.model.Mattress;
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
import com.mobile.backend.repository.ImageRepository;
import com.mobile.backend.repository.InventoryRepository;
import com.mobile.backend.repository.MattressRepository;
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
	MattressRepository mattressRepository;
	
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
		
		// Create and save a brand object for Tempur-Pedic
		Brand brandTempurPedic = new Brand();
		brandTempurPedic.setName("Tempur-Pedic");
		brandRepository.save(brandTempurPedic);
		
		// Create and save a brand object for Serta
		Brand brandSerta = new Brand();
		brandSerta.setName("Serta");
		brandRepository.save(brandSerta);

		// Create and save a brand object for Sealy
		Brand brandSealy = new Brand();
		brandSealy.setName("Sealy");
		brandRepository.save(brandSealy);

		// Create and save a brand object for Simmons
		Brand brandSimmons = new Brand();
		brandSimmons.setName("Simmons");
		brandRepository.save(brandSimmons);
		
		// Create and save a brand object for Casper
		Brand brandCasper = new Brand();
		brandCasper.setName("Casper");
		brandRepository.save(brandCasper);
	}
	
	@Order(3)
	@Test
	public void addSize() {
		Size sizeTwin = new Size();
		sizeTwin.setName("Twin");
		sizeRepository.save(sizeTwin);

		Size sizeTwinXL = new Size();
		sizeTwinXL.setName("Twin XL");
		sizeRepository.save(sizeTwinXL);

		Size sizeFull = new Size();
		sizeFull.setName("Full");
		sizeRepository.save(sizeFull);

		Size sizeQueen = new Size();
		sizeQueen.setName("Queen");
		sizeRepository.save(sizeQueen);

		Size sizeKing = new Size();
		sizeKing.setName("King");
		sizeRepository.save(sizeKing);
		
	}
	
	@Order(4)
	@Test
	public void addCategory() {
		
		Image image1 = new Image();
		image1.setTitle("1.png");
		image1.setPath(AppConstant.UPLOAD_CATEGORY_DIRECTORY+"/1.png");
		imageRepository.save(image1);
		
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
		
		Category memoryFoamMattress = new Category();
		memoryFoamMattress.setName("Memory Foam Mattress");
		memoryFoamMattress.setImage(image1);
		categoryRepository.save(memoryFoamMattress);
		
		Category hybridMattress = new Category();
		hybridMattress.setName("Hybrid Mattress");
		hybridMattress.setImage(image2);
		categoryRepository.save(hybridMattress);
		
		Category latexMattress = new Category();
		latexMattress.setName("Latex Mattress");
		latexMattress.setImage(image3);
		categoryRepository.save(latexMattress);
		
		Category innerspringMattress = new Category();
		innerspringMattress.setName("Innerspring Mattress");
		innerspringMattress.setImage(image4);
		categoryRepository.save(innerspringMattress);

	}
	
	@Order(5)
	@Test
	public void addmattress() {
		
		Image image1 = new Image();
		image1.setTitle("1.png");
		image1.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/1.png");
		imageRepository.save(image1);
		
		Image image2 = new Image();
		image2.setTitle("2.png");
		image2.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/2.png");
		imageRepository.save(image2);

		Image image3 = new Image();
		image3.setTitle("3.png");
		image3.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/3.png");
		imageRepository.save(image3);

		Image image4 = new Image();
		image4.setTitle("4.png");
		image4.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/4.png");
		imageRepository.save(image4);

		Image image5 = new Image();
		image5.setTitle("5.png");
		image5.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/5.png");
		imageRepository.save(image5);

		Image image6 = new Image();
		image6.setTitle("6.png");
		image6.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/6.png");
		imageRepository.save(image6);

		Image image7 = new Image();
		image7.setTitle("7.png");
		image7.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/7.png");
		imageRepository.save(image7);

		Image image8 = new Image();
		image8.setTitle("8.png");
		image8.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/8.png");
		imageRepository.save(image8);

		Image image9 = new Image();
		image9.setTitle("9.png");
		image9.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/9.png");
		imageRepository.save(image9);

		Image image10 = new Image();
		image10.setTitle("10.png");
		image10.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/10.png");
		imageRepository.save(image10);

		Image image11 = new Image();
		image11.setTitle("11.png");
		image11.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/11.png");
		imageRepository.save(image11);

		Image image12 = new Image();
		image12.setTitle("12.png");
		image12.setPath(AppConstant.UPLOAD_MATTRESS_DIRECTORY+"/12.png");
		imageRepository.save(image12);
		
		Brand brandTempurPedic = brandRepository.findByName("Tempur-Pedic").get();
		Brand brandSerta = brandRepository.findByName("Serta").get();
		Brand brandSealy = brandRepository.findByName("Sealy").get();
		Brand brandSimmons = brandRepository.findByName("Simmons").get();
		Brand brandCasper = brandRepository.findByName("Casper").get();
		
		Category memoryFoamMattress = categoryRepository.findByName("Memory Foam Mattress").get();
		Category hybridMattress = categoryRepository.findByName("Hybrid Mattress").get();
		Category latexMattress = categoryRepository.findByName("Latex Mattress").get();
		Category innerspringMattress = categoryRepository.findByName("Innerspring Mattress").get();
		
		
		// Create and save a memory foam mattress from Tempur-Pedic
		Mattress mattress1 = new Mattress();
		mattress1.setName("Tempur-Pedic Memory Foam Mattress");
		mattress1.setPrice(1500.0);
		mattress1.setDescription("The ultimate in comfort and support");
		mattress1.setBrand(brandTempurPedic);
		mattress1.setInventories(null);
		mattress1.setCategory(memoryFoamMattress);
		mattress1.setImage(image1);
		mattress1.setSoldQuantity(1L);
		mattressRepository.save(mattress1);

		// Create and save a hybrid mattress from Serta
		Mattress mattress2 = new Mattress();
		mattress2.setName("Serta Hybrid Mattress");
		mattress2.setPrice(1200.0);
		mattress2.setDescription("The perfect combination of foam and springs");
		mattress2.setBrand(brandSerta);
		mattress2.setInventories(null);
		mattress2.setCategory(hybridMattress);
		mattress2.setImage(image2);
		mattress2.setSoldQuantity(2L);
		mattressRepository.save(mattress2);

		// Create and save a latex mattress from Sealy
		Mattress mattress3 = new Mattress();
		mattress3.setName("Sealy Latex Mattress");
		mattress3.setPrice(1300.0);
		mattress3.setDescription("A comfortable and eco-friendly choice");
		mattress3.setBrand(brandSealy);
		mattress3.setInventories(null);
		mattress3.setCategory(latexMattress);
		mattress3.setImage(image3);
		mattress3.setSoldQuantity(2L);
		mattressRepository.save(mattress3);

		// Create and save an innerspring mattress from Simmons
		Mattress mattress4 = new Mattress();
		mattress4.setName("Simmons Innerspring Mattress");
		mattress4.setPrice(1000.0);
		mattress4.setDescription("Traditional comfort and support");
		mattress4.setBrand(brandSimmons);
		mattress4.setInventories(null);
		mattress4.setCategory(innerspringMattress);
		mattress4.setImage(image4);
		mattress4.setSoldQuantity(1L);
		mattressRepository.save(mattress4);

		// Create and save a memory foam mattress from Casper
		Mattress mattress5 = new Mattress();
		mattress5.setName("Casper Memory Foam Mattress");
		mattress5.setPrice(1100.0);
		mattress5.setDescription("Unbeatable comfort and breathability");
		mattress5.setBrand(brandCasper);
		mattress5.setInventories(null);
		mattress5.setCategory(memoryFoamMattress);
		mattress5.setImage(image5);
		mattress5.setSoldQuantity(2L);
		mattressRepository.save(mattress5);

		// Create and save a hybrid mattress from Tempur-Pedic
		Mattress mattress6 = new Mattress();
		mattress6.setName("Tempur-Pedic Hybrid Mattress");
		mattress6.setPrice(2000.0);
		mattress6.setDescription("The ultimate in comfort and support, with the perfect combination of foam and springs");
		mattress6.setBrand(brandTempurPedic);
		mattress6.setInventories(null);
		mattress6.setCategory(hybridMattress);
		mattress6.setImage(image6);
		mattress6.setSoldQuantity(3L);
		mattressRepository.save(mattress6);

		// Create and save a memory foam mattress from Serta
		Mattress mattress7 = new Mattress();
		mattress7.setName("Serta Memory Foam Mattress");
		mattress7.setPrice(1300.0);
		mattress7.setDescription("Experience the luxury of Serta's premium memory foam mattress");
		mattress7.setBrand(brandSerta);
		mattress7.setInventories(null);
		mattress7.setCategory(memoryFoamMattress);
		mattress7.setImage(image7);
		mattress7.setSoldQuantity(2L);
		mattressRepository.save(mattress7);

		// Create and save a latex mattress from Sealy
		Mattress mattress8 = new Mattress();
		mattress8.setName("Sealy Latex Mattress");
		mattress8.setPrice(1400.0);
		mattress8.setDescription("Experience the comfort and support of Sealy's eco-friendly latex mattress");
		mattress8.setBrand(brandSealy);
		mattress8.setInventories(null);
		mattress8.setCategory(latexMattress);
		mattress8.setImage(image8);
		mattress8.setSoldQuantity(3L);
		mattressRepository.save(mattress8);

		// Create and save an innerspring mattress from Simmons
		Mattress mattress9 = new Mattress();
		mattress9.setName("Simmons Beautyrest Innerspring Mattress");
		mattress9.setPrice(1100.0);
		mattress9.setDescription("Experience the traditional comfort and support of Simmons Beautyrest innerspring mattress");
		mattress9.setBrand(brandSimmons);
		mattress9.setInventories(null);
		mattress9.setCategory(innerspringMattress);
		mattress9.setImage(image9);
		mattress9.setSoldQuantity(2L);
		mattressRepository.save(mattress9);

		// Create and save a memory foam mattress from Casper
		Mattress mattress10 = new Mattress();
		mattress10.setName("Casper Wave Memory Foam Mattress");
		mattress10.setPrice(1800.0);
		mattress10.setDescription("Experience the ultimate luxury with Casper Wave's premium memory foam mattress");
		mattress10.setBrand(brandCasper);
		mattress10.setInventories(null);
		mattress10.setCategory(memoryFoamMattress);
		mattress10.setImage(image10);
		mattress10.setSoldQuantity(1L);
		mattressRepository.save(mattress10);

		// Create and save a hybrid mattress from Sealy
		Mattress mattress11 = new Mattress();
		mattress11.setName("Sealy Hybrid Mattress");
		mattress11.setPrice(1700.0);
		mattress11.setDescription("Experience the perfect combination of comfort and support with Sealy's hybrid mattress");
		mattress11.setBrand(brandSealy);
		mattress11.setInventories(null);
		mattress11.setCategory(hybridMattress);
		mattress11.setImage(image11);
		mattress11.setSoldQuantity(1L);
		mattressRepository.save(mattress11);
		
		// Create and save a hybrid mattress from Beautyrest
		Mattress mattress12 = new Mattress();
		mattress12.setName("Beautyrest Hybrid Mattress");
		mattress12.setPrice(1800.0);
		mattress12.setDescription("Luxury comfort and support");
		mattress12.setBrand(brandSealy);
		mattress12.setInventories(null);
		mattress12.setCategory(hybridMattress);
		mattress12.setImage(image12);
		mattress12.setSoldQuantity(1L);
		mattressRepository.save(mattress12);
	}
	
	@Order(6)
	@Test
	public void addInventory() {
		Size sizeTwin = sizeRepository.findByName("Twin");
		Size sizeTwinXL = sizeRepository.findByName("Twin XL");
		Size sizeFull = sizeRepository.findByName("Full");
		Size sizeQueen = sizeRepository.findByName("Queen");
		Size sizeKing = sizeRepository.findByName("King");
		
		// Get mattresss
		Mattress mattress1 = mattressRepository.findById(1L).get();
		Mattress mattress2 = mattressRepository.findById(2L).get();
		Mattress mattress3 = mattressRepository.findById(3L).get();
		Mattress mattress4 = mattressRepository.findById(4L).get();
		Mattress mattress5 = mattressRepository.findById(5L).get();
		Mattress mattress6 = mattressRepository.findById(6L).get();
		Mattress mattress7 = mattressRepository.findById(7L).get();
		Mattress mattress8 = mattressRepository.findById(8L).get();
		Mattress mattress9 = mattressRepository.findById(9L).get();
		Mattress mattress10 = mattressRepository.findById(10L).get();
		Mattress mattress11 = mattressRepository.findById(11L).get();
		Mattress mattress12 = mattressRepository.findById(12L).get();
		// Create inventory 
		Inventory inventory1 = new Inventory();
		inventory1.setQuantity(10);
		inventory1.setMattress(mattress1);
		inventory1.setSize(sizeTwin);
		inventoryRepository.save(inventory1);
		
		// Create inventory items
		Inventory inventory2 = new Inventory();
		inventory2.setQuantity(5);
		inventory2.setMattress(mattress2);
		inventory2.setSize(sizeTwinXL);
		inventoryRepository.save(inventory2);

		Inventory inventory3 = new Inventory();
		inventory3.setQuantity(15);
		inventory3.setMattress(mattress3);
		inventory3.setSize(sizeFull);
		inventoryRepository.save(inventory3);

		Inventory inventory4 = new Inventory();
		inventory4.setQuantity(20);
		inventory4.setMattress(mattress4);
		inventory4.setSize(sizeQueen);
		inventoryRepository.save(inventory4);

		Inventory inventory5 = new Inventory();
		inventory5.setQuantity(8);
		inventory5.setMattress(mattress5);
		inventory5.setSize(sizeKing);
		inventoryRepository.save(inventory5);

		Inventory inventory6 = new Inventory();
		inventory6.setQuantity(3);
		inventory6.setMattress(mattress6);
		inventory6.setSize(sizeTwin);
		inventoryRepository.save(inventory6);
		
		Inventory inventory7 = new Inventory();
		inventory7.setQuantity(3);
		inventory7.setMattress(mattress1);
		inventory7.setSize(sizeTwinXL);
		inventoryRepository.save(inventory7);
		
		Inventory inventory8 = new Inventory();
		inventory8.setQuantity(4);
		inventory8.setMattress(mattress8);
		inventory8.setSize(sizeFull);
		inventoryRepository.save(inventory8);
		
		Inventory inventory9 = new Inventory();
		inventory9.setQuantity(30);
		inventory9.setMattress(mattress9);
		inventory9.setSize(sizeKing);
		inventoryRepository.save(inventory9);
		
		Inventory inventory10 = new Inventory();
		inventory10.setQuantity(30);
		inventory10.setMattress(mattress10);
		inventory10.setSize(sizeQueen);
		inventoryRepository.save(inventory10);
		
		Inventory inventory11 = new Inventory();
		inventory11.setQuantity(30);
		inventory11.setMattress(mattress11);
		inventory11.setSize(sizeTwin);
		inventoryRepository.save(inventory11);
		
		Inventory inventory12 = new Inventory();
		inventory12.setQuantity(30);
		inventory12.setMattress(mattress12);
		inventory12.setSize(sizeTwinXL);
		inventoryRepository.save(inventory12);
		
		Inventory inventory13 = new Inventory();
		inventory13.setQuantity(15);
		inventory13.setMattress(mattress7);
		inventory13.setSize(sizeQueen);
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
		
		// Get mattresss
		Mattress mattress1 = mattressRepository.findById(1L).get();
		Mattress mattress2 = mattressRepository.findById(2L).get();
		Mattress mattress3 = mattressRepository.findById(3L).get();

		
		// For user Khai
		Cart cartKhai = cartRepository.findById(1L).get();
		

		Size sizeTwin = sizeRepository.findByName("Twin");
		Size sizeTwinXL = sizeRepository.findByName("Twin XL");
		Size sizeFull = sizeRepository.findByName("Full");
		
		CartItem cartItem1 = new CartItem();
		cartItem1.setMattress(mattress1);
		cartItem1.setChoice_size(sizeTwin);
		cartItem1.setCart(cartKhai);
		cartItem1.setQuantity(1);
		cartItemRepository.save(cartItem1);
		
		CartItem cartItem2 = new CartItem();
		cartItem2.setMattress(mattress2);
		cartItem2.setCart(cartKhai);
		cartItem2.setChoice_size(sizeTwinXL);
		cartItem2.setQuantity(2);
		cartItemRepository.save(cartItem2);
		
		CartItem cartItem3 = new CartItem();
		cartItem3.setMattress(mattress3);
		cartItem3.setCart(cartKhai);
		cartItem3.setChoice_size(sizeFull);
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
		
		
		Mattress mattress1 = mattressRepository.findById(1L).get();
		Mattress mattress2 = mattressRepository.findById(2L).get();
		Mattress mattress3 = mattressRepository.findById(3L).get();
		
		Size sizeTwin = sizeRepository.findByName("Twin");
		Size sizeTwinXL = sizeRepository.findByName("Twin XL");
		Size sizeFull = sizeRepository.findByName("Full");
		
		com.mobile.backend.model.order.Order orderKhai = orderRepository.findById(1L).get();
		
	
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setQuantity(2);
		orderItem1.setMattress(mattress1);
		orderItem1.setOrder(orderKhai);
		orderItem1.setChoice_size(sizeTwin);
		orderItemRepository.save(orderItem1);
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setQuantity(2);
		orderItem2.setMattress(mattress2);
		orderItem2.setOrder(orderKhai);
		orderItem2.setChoice_size(sizeTwinXL);
		orderItemRepository.save(orderItem2);
		
		OrderItem orderItem3 = new OrderItem();
		orderItem3.setQuantity(2);
		orderItem3.setMattress(mattress3);
		orderItem3.setOrder(orderKhai);
		orderItem3.setChoice_size(sizeFull);
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
