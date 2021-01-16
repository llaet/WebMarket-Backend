package com.saleshub.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import com.saleshub.domain.enums.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Address;
import com.saleshub.domain.BankSlipPayment;
import com.saleshub.domain.Category;
import com.saleshub.domain.City;
import com.saleshub.domain.CreditCardPayment;
import com.saleshub.domain.Customer;
import com.saleshub.domain.OrderedItem;
import com.saleshub.domain.Payment;
import com.saleshub.domain.Product;
import com.saleshub.domain.SaleOrder;
import com.saleshub.domain.State;
import com.saleshub.domain.enums.ClientType;
import com.saleshub.domain.enums.PaymentStatus;
import com.saleshub.repositories.AddressRepository;
import com.saleshub.repositories.CategoryRepository;
import com.saleshub.repositories.CityRepository;
import com.saleshub.repositories.CustomerRepository;
import com.saleshub.repositories.OrderedItemRepository;
import com.saleshub.repositories.PaymentRepository;
import com.saleshub.repositories.ProductRepository;
import com.saleshub.repositories.SaleOrderRepository;
import com.saleshub.repositories.StateRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private SaleOrderRepository saleOrderRepository;
	@Autowired
	private OrderedItemRepository orderedItemRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void instantiateDatabase() throws Exception {

			Category category1 = new Category(null,"Informática");
			Category category2 = new Category(null,"Escritório");
			Category category3 = new Category(null,"Cama mesa e banho");
			Category category4 = new Category(null,"Eletrônicos");
			Category category5 = new Category(null,"Jardinagem");
			Category category6 = new Category(null,"Decoração");
			Category category7 = new Category(null,"Perfumaria");
			
			Product product1 = new Product(null,"Computador",2000.0);
			Product product2 = new Product(null,"Impressora",800.0);
			Product product3 = new Product(null,"Mouse",80.0);
			Product product4 = new Product(null,"Mesa de escritório",300.0);
			Product product5 = new Product(null,"Toalha",50.0);
			Product product6 = new Product(null,"Colcha",200.0);
			Product product7 = new Product(null,"Tv true color",1200.0);
			Product product8 = new Product(null,"Roçadeira",800.0);
			Product product9 = new Product(null,"Abajour",100.0);
			Product product10 = new Product(null,"Pendente",180.0);
			Product product11 = new Product(null,"Shampoo",90.0);

			Product p12 = new Product(null, "Product 12", 10.00);
			Product p13 = new Product(null, "Product 13", 10.00);
			Product p14 = new Product(null, "Product 14", 10.00);
			Product p15 = new Product(null, "Product 15", 10.00);
			Product p16 = new Product(null, "Product 16", 10.00);
			Product p17 = new Product(null, "Product 17", 10.00);
			Product p18 = new Product(null, "Product 18", 10.00);
			Product p19 = new Product(null, "Product 19", 10.00);
			Product p20 = new Product(null, "Product 20", 10.00);
			Product p21 = new Product(null, "Product 21", 10.00);
			Product p22 = new Product(null, "Product 22", 10.00);
			Product p23 = new Product(null, "Product 23", 10.00);
			Product p24 = new Product(null, "Product 24", 10.00);
			Product p25 = new Product(null, "Product 25", 10.00);
			Product p26 = new Product(null, "Product 26", 10.00);
			Product p27 = new Product(null, "Product 27", 10.00);
			Product p28 = new Product(null, "Product 28", 10.00);
			Product p29 = new Product(null, "Product 29", 10.00);
			Product p30 = new Product(null, "Product 30", 10.00);
			Product p31 = new Product(null, "Product 31", 10.00);
			Product p32 = new Product(null, "Product 32", 10.00);
			Product p33 = new Product(null, "Product 33", 10.00);
			Product p34 = new Product(null, "Product 34", 10.00);
			Product p35 = new Product(null, "Product 35", 10.00);
			Product p36 = new Product(null, "Product 36", 10.00);
			Product p37 = new Product(null, "Product 37", 10.00);
			Product p38 = new Product(null, "Product 38", 10.00);
			Product p39 = new Product(null, "Product 39", 10.00);
			Product p40 = new Product(null, "Product 40", 10.00);
			Product p41 = new Product(null, "Product 41", 10.00);
			Product p42 = new Product(null, "Product 42", 10.00);
			Product p43 = new Product(null, "Product 43", 10.00);
			Product p44 = new Product(null, "Product 44", 10.00);
			Product p45 = new Product(null, "Product 45", 10.00);
			Product p46 = new Product(null, "Product 46", 10.00);
			Product p47 = new Product(null, "Product 47", 10.00);
			Product p48 = new Product(null, "Product 48", 10.00);
			Product p49 = new Product(null, "Product 49", 10.00);
			Product p50 = new Product(null, "Product 50", 10.00);
			
			//products to categories manual test association - Many to Many
			category1.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39,
				p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
			category1.getProducts().addAll(Arrays.asList(product1,product2,product3));
			category2.getProducts().addAll(Arrays.asList(product2,product4));
			category3.getProducts().addAll(Arrays.asList(product5,product6));
			category4.getProducts().addAll(Arrays.asList(product1,product2,product3,product7));
			category5.getProducts().addAll(Arrays.asList(product8));
			category6.getProducts().addAll(Arrays.asList(product9,product10));
			category7.getProducts().addAll(Arrays.asList(product11));
			//categories to products manual test association - Many to Many
			p12.getCategories().add(category1);
			p13.getCategories().add(category1);
			p14.getCategories().add(category1);
			p15.getCategories().add(category1);
			p16.getCategories().add(category1);
			p17.getCategories().add(category1);
			p18.getCategories().add(category1);
			p19.getCategories().add(category1);
			p20.getCategories().add(category1);
			p21.getCategories().add(category1);
			p22.getCategories().add(category1);
			p23.getCategories().add(category1);
			p24.getCategories().add(category1);
			p25.getCategories().add(category1);
			p26.getCategories().add(category1);
			p27.getCategories().add(category1);
			p28.getCategories().add(category1);
			p29.getCategories().add(category1);
			p30.getCategories().add(category1);
			p31.getCategories().add(category1);
			p32.getCategories().add(category1);
			p33.getCategories().add(category1);
			p34.getCategories().add(category1);
			p35.getCategories().add(category1);
			p36.getCategories().add(category1);
			p37.getCategories().add(category1);
			p38.getCategories().add(category1);
			p39.getCategories().add(category1);
			p40.getCategories().add(category1);
			p41.getCategories().add(category1);
			p42.getCategories().add(category1);
			p43.getCategories().add(category1);
			p44.getCategories().add(category1);
			p45.getCategories().add(category1);
			p46.getCategories().add(category1);
			p47.getCategories().add(category1);
			p48.getCategories().add(category1);
			p49.getCategories().add(category1);
			p50.getCategories().add(category1);
			product1.getCategories().addAll(Arrays.asList(category1,category4));
			product2.getCategories().addAll(Arrays.asList(category1,category2,category4));
			product3.getCategories().addAll(Arrays.asList(category1,category4));
			product4.getCategories().addAll(Arrays.asList(category2));
			product5.getCategories().addAll(Arrays.asList(category3));
			product6.getCategories().addAll(Arrays.asList(category3));
			product7.getCategories().addAll(Arrays.asList(category4));
			product8.getCategories().addAll(Arrays.asList(category5));
			product9.getCategories().addAll(Arrays.asList(category6));
			product10.getCategories().addAll(Arrays.asList(category6));
			product11.getCategories().addAll(Arrays.asList(category7));
			
			this.categoryRepository.saveAll(Arrays.asList(
					category1,category2,category3,category4,category5,category6,category7));
			this.productRepository.saveAll(Arrays.asList(
					product1,product2,product3,product4,product5,product6,product7,product8,
					product9,product10,product11));
			this.productRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

			State state1 = new State(null,"Minas Gerais");
			State state2 = new State(null,"São Paulo");
			
			City city1 = new City(null,"Belo Horizonte",state1);
			City city2 = new City(null,"São Paulo",state2);
			City city3 = new City(null,"Campinas",state2);
			//cities to state association - Many to One
			state1.getCities().addAll(Arrays.asList(city1));
			state2.getCities().addAll(Arrays.asList(city2,city3));
			
			this.stateRepository.saveAll(Arrays.asList(state1,state2));
			this.cityRepository.saveAll(Arrays.asList(city1,city2,city3));
			
			Customer customer1 = new Customer(null,"Maria Silva","maria@maria.com",
					"05458216067",
					ClientType.PESSOA_FISICA, this.passwordEncoder.encode("teste123"));
			customer1.getPhones().addAll(Arrays.asList("91985219978","91985277978"));

		Customer customer2 = new Customer(null,"José Silva","jose@jose.com",
				"19064760047", ClientType.PESSOA_FISICA,
				this.passwordEncoder.encode("12345"));
		customer2.getPhones().add("91985216578");
		customer2.addProfile(CustomerProfile.ADMIN);
			
			Address address = new Address(null,"Rua Flores","300","Apto 303",
					"Jardim","38220834",
					customer1,city1);
			Address address2 = new Address(null,"Avenida Matos","105",
					"sala 800", "Centro","38220777",
					customer1,city2);
		Address address3 = new Address(null,"Alameda Alvaro Rocco","121",
				"Apto 24", "Cidade Industrial","38227721",
				customer2,city3);
			
			customer1.getAddresses().addAll(Arrays.asList(address,address2));
			customer2.getAddresses().addAll(Arrays.asList(address3));
			
			this.customerRepository.saveAll(Arrays.asList(customer1,customer2));
			this.addressRepository.saveAll(Arrays.asList(address,address2,address3));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			sdf.setTimeZone(TimeZone.getTimeZone("UTF"));
			
			SaleOrder order1 = new SaleOrder(null, sdf.parse("30/09/2019 23:58"),
					customer1,address);
			SaleOrder order2 = new SaleOrder(null, sdf.parse("15/04/2020 12:31"),
					customer1,address2);
			SaleOrder order3 = new SaleOrder(null, sdf.parse("04/09/2020 12:00"),
				customer2,address3);
			
			Payment payment1 = new CreditCardPayment(null,PaymentStatus.QUITADO,order1,
					6);
			order1.setPayment(payment1);
			Payment payment2 = new BankSlipPayment(null,PaymentStatus.PENDENTE,order2,
					sdf.parse("20/04/2020 00:00"),null);
			order2.setPayment(payment2);
			order3.setPayment(payment1);
			
			customer1.getOrders().addAll(Arrays.asList(order1,order2));
			customer2.getOrders().addAll(Arrays.asList(order3));
			
			this.saleOrderRepository.saveAll(Arrays.asList(order1,order2,order3));
			this.paymentRepository.saveAll(Arrays.asList(payment1,payment2));
			
			OrderedItem orderedItem1 = new OrderedItem(order1,product1,0.00,1,
					2000.00);
			OrderedItem orderedItem2 = new OrderedItem(order1,product3,0.00,2,
					80.00);
			OrderedItem orderedItem3 = new OrderedItem(order2,product2,100.00,1,
					800.00);
			OrderedItem orderedItem4 = new OrderedItem(order3,product4,100.00,1,
				1000.00);
			
			order1.getItems().addAll(Arrays.asList(orderedItem1,orderedItem2));
			order2.getItems().add(orderedItem3);
			order3.getItems().add(orderedItem4);
			
			product1.getItems().add(orderedItem1);
			product2.getItems().add(orderedItem3);
			product3.getItems().add(orderedItem2);
			product4.getItems().add(orderedItem4);
			
			this.orderedItemRepository.saveAll(
					Arrays.asList(orderedItem1,orderedItem2,orderedItem3,orderedItem4));
		}
}
