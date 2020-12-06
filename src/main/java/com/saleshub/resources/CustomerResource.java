package com.saleshub.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saleshub.domain.Customer;
import com.saleshub.domain.dto.CustomerDTO;
import com.saleshub.domain.dto.CustomerNewDTO;
import com.saleshub.services.CustomerService;

@RestController
@RequestMapping("/clientes")
public class CustomerResource {

	@Autowired
	private CustomerService service;

	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CustomerNewDTO customerNewDTO){
		
		Customer createdCustomer = service.toCustomer(customerNewDTO);
		
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

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		
		this.service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.service.findById(id));
	}

	@GetMapping("/email")
	public ResponseEntity<Customer> findByEmail(@RequestParam(value = "email") String email){
		Customer customer = this.service.findByAuthenticatedUserEmail(email);
		return ResponseEntity.ok(customer);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAll(){
		
		List<CustomerDTO> categoriesDTO = this.service.findAll().stream()
				.map(customer -> new CustomerDTO(customer))
				.collect(Collectors.toList());
			
			return ResponseEntity.ok(categoriesDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
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

	@PostMapping("/picture")
	public ResponseEntity<URI> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file){

		URI uri = this.service.uploadProfilePicture(file);

		return ResponseEntity.created(uri).build();
	}
	
}
