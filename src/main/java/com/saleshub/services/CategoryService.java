package com.saleshub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Category;
import com.saleshub.repositories.CategoryRepository;
import com.saleshub.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Category create(Category category) {
		category.setId(null);
		return this.repository.saveAndFlush(category);
	}

	public Category update(Category category, Integer id) {
		findById(id);
		return this.repository.saveAndFlush(category);
	}
	
	public Category findById(Integer id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada. Id: " + id));
	}
}
