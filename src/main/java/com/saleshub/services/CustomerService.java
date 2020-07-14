package com.saleshub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Customer;
import com.saleshub.repositories.CustomerRepository;
import com.saleshub.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	public Customer findById(Integer id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado. Id: " + id));
	}
}
