package com.saleshub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Category;
import com.saleshub.repositories.CategoryRepository;
import com.saleshub.services.exceptions.ObjectNotFoundException;
import com.saleshub.services.exceptions.DataIntegrityException;

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

	public void deleteById(Integer id) {
		findById(id);
		try {
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir uma categoria com produtos "
					+ "vinculados.");
		}
	}
}
