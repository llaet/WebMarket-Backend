package com.saleshub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Category;
import com.saleshub.domain.dto.CategoryDTO;
import com.saleshub.repositories.CategoryRepository;
import com.saleshub.services.exceptions.DataIntegrityException;
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
		
		category.setId(id);
		
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

	public List<Category> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest
				.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return this.repository.findAll(pageRequest);
	}
	
	public Category toCategory(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}
}
