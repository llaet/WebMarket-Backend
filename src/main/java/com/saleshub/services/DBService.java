package com.saleshub.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
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
			
			//products to categories manual test association - Many to Many
			category1.getProducts().addAll(Arrays.asList(product1,product2,product3));
			category2.getProducts().addAll(Arrays.asList(product2,product4));
			category3.getProducts().addAll(Arrays.asList(product5,product6));
			category4.getProducts().addAll(Arrays.asList(product1,product2,product3,product7));
			category5.getProducts().addAll(Arrays.asList(product8));
			category6.getProducts().addAll(Arrays.asList(product9,product10));
			category7.getProducts().addAll(Arrays.asList(product11));
			//categories to products manual test association - Many to Many
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
			
			Customer customer1 = new Customer(null,"Maria Silva","lucas.laetlira28@gmail.com", "91985219978",
					ClientType.PESSOA_FISICA);
			customer1.getPhones().addAll(Arrays.asList("91985219978","91985277978"));
			
			Address address = new Address(null,"Rua Flores","300","Apto 303","Jardim","38220834",
					customer1,city1);
			Address address2 = new Address(null,"Avenida Matos","105","sala 800","Centro","38220777",
					customer1,city2);
			
			customer1.getAddresses().addAll(Arrays.asList(address,address2));
			
			this.customerRepository.save(customer1);
			this.addressRepository.saveAll(Arrays.asList(address,address2));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			sdf.setTimeZone(TimeZone.getTimeZone("UTF"));
			
			SaleOrder order1 = new SaleOrder(null, sdf.parse("30/09/2019 23:58"),customer1,address);
			SaleOrder order2 = new SaleOrder(null, sdf.parse("15/04/2020 12:31"),customer1,address2);
			
			Payment payment1 = new CreditCardPayment(null,PaymentStatus.QUITADO,order1,6);
			order1.setPayment(payment1);
			Payment payment2 = new BankSlipPayment(null,PaymentStatus.PENDENTE,order2,
					sdf.parse("20/04/2020 00:00"),null);
			order2.setPayment(payment2);
			
			customer1.getOrders().addAll(Arrays.asList(order1,order2));
			
			this.saleOrderRepository.saveAll(Arrays.asList(order1,order2));
			this.paymentRepository.saveAll(Arrays.asList(payment1,payment2));
			
			OrderedItem orderedItem1 = new OrderedItem(order1,product1,0.00,1,2000.00);
			OrderedItem orderedItem2 = new OrderedItem(order1,product3,0.00,2,80.00);
			OrderedItem orderedItem3 = new OrderedItem(order2,product2,100.00,1,800.00);
			
			order1.getItems().addAll(Arrays.asList(orderedItem1,orderedItem2));
			order2.getItems().add(orderedItem3);
			
			product1.getItems().add(orderedItem1);
			product2.getItems().add(orderedItem3);
			product3.getItems().add(orderedItem2);
			
			this.orderedItemRepository.saveAll(Arrays.asList(orderedItem1,orderedItem2,orderedItem3));
		}
}
