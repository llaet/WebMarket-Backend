package com.saleshub.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.saleshub.domain.Customer;
import com.saleshub.domain.dto.CustomerDTO;
import com.saleshub.services.CustomerService;

@RestController
@RequestMapping("/clientes")
public class CustomerResource {

	@Autowired
	private CustomerService service;

	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CustomerDTO customerDTO){
		
		Customer createdCustomer = service.toCustomer(customerDTO);
		
		createdCustomer = this.service.create(createdCustomer);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCustomer.getId()).toUri();
		
		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO customerDTO, 
			@PathVariable("id") Integer id){
		
		Customer createdCustomer = service.toCustomer(customerDTO);
		
		createdCustomer = this.service.update(createdCustomer,id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		
		this.service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAll(){
		
		List<CustomerDTO> categoriesDTO = this.service.findAll().stream()
				.map(customer -> new CustomerDTO(customer))
				.collect(Collectors.toList());
			
			return ResponseEntity.ok(categoriesDTO);
	}
	
	@GetMapping("/paginar")
	public ResponseEntity<Page<CustomerDTO>> findAllWithPageInfo(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction){
		
		Page<Customer> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		Page<CustomerDTO> categoriesDTO = list.map(customer -> new CustomerDTO(customer));
				
		return ResponseEntity.ok(categoriesDTO);
	}
	
}
