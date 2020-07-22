package com.saleshub.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saleshub.domain.Category;
import com.saleshub.services.CategoryService;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Category category){
		Category createdCategory = this.service.create(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCategory.getId()).toUri();
			return ResponseEntity.created(uri).build();	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.service.findById(id));
	}
}
