package com.saleshub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Category;
import com.saleshub.repositories.CategoryRepository;
import com.saleshub.services.exceptions.CategoryNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Category findById(Integer id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada. Id: " + id));
	}
}
