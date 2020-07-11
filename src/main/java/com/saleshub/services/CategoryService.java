package com.saleshub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Category;
import com.saleshub.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Optional<Category> findById(Integer id) {
		return this.repository.findById(id);
	}
}
