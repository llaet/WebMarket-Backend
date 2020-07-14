package com.saleshub.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saleshub.domain.Customer;
import com.saleshub.services.CustomerService;

@RestController
@RequestMapping("/clientes")
public class CustomerResource {

	@Autowired
	private CustomerService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.service.findById(id));
	}
}
