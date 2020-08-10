package com.saleshub.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.saleshub.domain.Customer;
import com.saleshub.services.validation.CustomerUpdate;

@CustomerUpdate
public class CustomerDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O campo nome não deve estar vazio")
	@Size(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	
	@Email(message = "Email inválido")
	@NotEmpty(message = "O campo email não deve estar vazio")
	private String email;
	
	public CustomerDTO() {}
	
	public CustomerDTO(Customer customer) {
		super();
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
