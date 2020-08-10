package com.saleshub.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.saleshub.domain.Customer;
import com.saleshub.domain.dto.CustomerNewDTO;
import com.saleshub.domain.enums.ClientType;
import com.saleshub.repositories.CustomerRepository;
import com.saleshub.resources.handler.FieldMessage;
import com.saleshub.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert,CustomerNewDTO>{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void initialize(CustomerInsert ann) {
	}

	@Override
	public boolean isValid(CustomerNewDTO value, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		if(value.getType().equals(ClientType.PESSOA_FISICA.getClientNumber())
				&& !BR.isValidCPF(value.getDocument())) {
			
			list.add(new FieldMessage("document","CPF inválido"));
			
		}
		
		if(value.getType().equals(ClientType.PESSOA_JURIDICA.getClientNumber())
				&& !BR.isValidCNPJ(value.getDocument())) {
			
			list.add(new FieldMessage("document","CNPJ inválido"));
			
		}
		
		Customer customer = this.customerRepository.findByEmail(value.getEmail());
		
		if(customer != null) {
			list.add(new FieldMessage("email","Email já existente: "
					+ customer.getEmail()));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
			}
		
		return list.isEmpty();
	}

}
