package com.saleshub;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saleshub.domain.Address;
import com.saleshub.domain.BankSlipPayment;
import com.saleshub.domain.Category;
import com.saleshub.domain.City;
import com.saleshub.domain.CreditCardPayment;
import com.saleshub.domain.Customer;
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
import com.saleshub.repositories.PaymentRepository;
import com.saleshub.repositories.ProductRepository;
import com.saleshub.repositories.SaleOrderRepository;
import com.saleshub.repositories.StateRepository;

@SpringBootApplication
public class SpringcourseApplication implements CommandLineRunner {
	
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

	public static void main(String[] args) {
		SpringApplication.run(SpringcourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category1 = new Category(null,"Informática");
		Category category2 = new Category(null,"Escritório");
		
		Product product1 = new Product(null,"Computador",2000.0);
		Product product2 = new Product(null,"Impressora",800.0);
		Product product3 = new Product(null,"Mouse",80.0);
		
		//products to categories manual test association - Many to Many
		category1.getProducts().addAll(Arrays.asList(product1,product2,product3));
		category2.getProducts().addAll(Arrays.asList(product2));
		//categories to products manual test association - Many to Many
		product1.getCategories().addAll(Arrays.asList(category2));
		product2.getCategories().addAll(Arrays.asList(category1,category2));
		product3.getCategories().addAll(Arrays.asList(category1));
		
		this.categoryRepository.saveAll(Arrays.asList(category1,category2));
		this.productRepository.saveAll(Arrays.asList(product1,product2,product3));

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
		
		Customer customer1 = new Customer(null,"Maria Silva","maria@maria.com", "91985219978",
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
	}

}
