package com.saleshub.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

	@GetMapping
	public ResponseEntity<Page<SaleOrder>> findAllWithPageInfo(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "orderedAt") String orderBy,
			@RequestParam(value="direction", defaultValue = "DESC") String direction){

		Page<SaleOrder> list = this.service.findPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok(list);
	}
}
