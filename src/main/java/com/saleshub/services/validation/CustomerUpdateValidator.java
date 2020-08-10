package com.saleshub.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.saleshub.domain.Customer;
import com.saleshub.domain.dto.CustomerDTO;
import com.saleshub.repositories.CustomerRepository;
import com.saleshub.resources.handler.FieldMessage;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate,CustomerDTO>{
	
	@Autowired
	private HttpServletRequest request; 
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void initialize(CustomerUpdate ann) {
	}

	@Override
	public boolean isValid(CustomerDTO value, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> requestMap = (Map<String,String>) this.request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(requestMap.get("id"));

		List<FieldMessage> list = new ArrayList<>();
		
		Customer customer = this.customerRepository.findByEmail(value.getEmail());
		
		if(customer != null && !customer.getId().equals(uriId)) {
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
