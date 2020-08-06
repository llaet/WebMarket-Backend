package com.saleshub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Customer;
import com.saleshub.domain.dto.CustomerDTO;
import com.saleshub.repositories.CustomerRepository;
import com.saleshub.services.exceptions.DataIntegrityException;
import com.saleshub.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	public Customer create(Customer customer) {
		customer.setId(null);
		return this.repository.saveAndFlush(customer);
	}

	public Customer update(Customer customer, Integer id) {
		
		findById(id);
		
		customer.setId(id);
		
		return this.repository.saveAndFlush(customer);
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir um cliente com produtos "
					+ "vinculados.");
		}
	}
	
	public Customer findById(Integer id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado. Id: " + id));
	}

	public List<Customer> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest
				.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return this.repository.findAll(pageRequest);
	}
	
	public Customer toCustomer(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getId(), customerDTO.getName(),
				customerDTO.getEmail(),null,null);
	}
}
