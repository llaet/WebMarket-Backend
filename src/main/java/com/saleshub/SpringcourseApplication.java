package com.saleshub;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saleshub.domain.Category;
import com.saleshub.domain.City;
import com.saleshub.domain.Product;
import com.saleshub.domain.State;
import com.saleshub.repositories.CategoryRepository;
import com.saleshub.repositories.CityRepository;
import com.saleshub.repositories.ProductRepository;
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
	}

}
