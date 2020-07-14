package com.saleshub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshub.domain.SaleOrder;
import com.saleshub.repositories.SaleOrderRepository;
import com.saleshub.services.exceptions.ObjectNotFoundException;

@Service
public class SaleOrderService {

	@Autowired
	private SaleOrderRepository repository;
	
	public SaleOrder findById(Integer id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado. Id: " + id));
	}
}
