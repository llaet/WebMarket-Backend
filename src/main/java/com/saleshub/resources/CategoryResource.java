package com.saleshub.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<Void> create(@RequestBody Category category){
		Category createdCategory = this.service.create(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCategory.getId()).toUri();
			return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Category category,
			@PathVariable("id") Integer id){
		this.service.update(category, id);
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
