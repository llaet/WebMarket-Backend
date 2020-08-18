package com.saleshub.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saleshub.domain.SaleOrder;
import com.saleshub.services.SaleOrderService;

@RestController
@RequestMapping("/pedidos")
public class SaleOrderResource {

	@Autowired
	private SaleOrderService service;
	
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody SaleOrder saleOrder){	
		
		SaleOrder createdSaleOrder = this.service.create(saleOrder);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdSaleOrder.getId()).toUri();
		
		return ResponseEntity.created(uri).build();	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SaleOrder> findById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.service.findById(id));
	}
}
