package com.saleshub.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saleshub.domain.Category;
import com.saleshub.domain.dto.CategoryDTO;
import com.saleshub.services.CategoryService;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CategoryDTO categoryDTO){	
		
		Category createdCategory = this.service.toCategory(categoryDTO);
		
		createdCategory = this.service.create(createdCategory);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCategory.getId()).toUri();
			return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable("id") Integer id){
		
		Category createdCategory = this.service.toCategory(categoryDTO);
		
		this.service.update(createdCategory, id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		
		List<CategoryDTO> categoriesDTO = this.service.findAll().stream()
			.map(category -> new CategoryDTO(category))
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(categoriesDTO);
	}
	
	@GetMapping("/paginar")
	public ResponseEntity<Page<CategoryDTO>> findAllWithPageInfo(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction){
		
		Page<Category> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> categoriesDTO = list.map(category -> new CategoryDTO(category));
				
		return ResponseEntity.ok(categoriesDTO);
	}
}
