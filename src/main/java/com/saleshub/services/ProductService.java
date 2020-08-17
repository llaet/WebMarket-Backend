package com.saleshub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Category;
import com.saleshub.domain.Product;
import com.saleshub.repositories.ProductRepository;
import com.saleshub.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryService categoryService;
	
	public Product findById(Integer id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado. Id: " + id));
	}
	
	public Page<Product> search(String name, List<Integer> ids,
			Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest
				.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Category> categories = this.categoryService.findAllById(ids);
		
		return this.repository.search(name,categories,pageRequest);
		
	}
}
