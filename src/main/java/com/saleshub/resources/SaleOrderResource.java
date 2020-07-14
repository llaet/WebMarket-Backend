package com.saleshub.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saleshub.domain.SaleOrder;
import com.saleshub.services.SaleOrderService;

@RestController
@RequestMapping("/pedidos")
public class SaleOrderResource {

	@Autowired
	private SaleOrderService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<SaleOrder> findById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.service.findById(id));
	}
}
