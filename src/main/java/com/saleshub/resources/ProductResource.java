package com.saleshub.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saleshub.domain.Product;
import com.saleshub.domain.dto.ProductDTO;
import com.saleshub.resources.utils.URL;
import com.saleshub.services.ProductService;

@RestController
@RequestMapping("/produtos")
public class ProductResource {

	@Autowired
	private ProductService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAllWithPageInfo(
			@RequestParam(value="nome", defaultValue = "") String name,
			@RequestParam(value="categorias", defaultValue = "") String categories,
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction){
		
		String decodedName = URL.decodeParam(name);
		
		List<Integer> ids = URL.decodeIntList(categories);
		
		Page<Product> list = this.service.search(decodedName,ids,page, linesPerPage, orderBy, direction);
		
		Page<ProductDTO> categoriesDTO = list.map(product -> new ProductDTO(
				product));
				
		return ResponseEntity.ok(categoriesDTO);
	}
}
