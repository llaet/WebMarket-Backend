package com.saleshub.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saleshub.domain.Category;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {
	
	@GetMapping
	public List<Category> findAll(){
		List<Category> categoryList = new ArrayList<>();
		
		categoryList.add(new Category(1,"Informática"));
		categoryList.add(new Category(2,"Escritório"));
		
		return categoryList;
	}
}
