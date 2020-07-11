package com.saleshub;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saleshub.domain.Category;
import com.saleshub.domain.Product;
import com.saleshub.repositories.CategoryRepository;
import com.saleshub.repositories.ProductRepository;

@SpringBootApplication
public class SpringcourseApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

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
	}

}
