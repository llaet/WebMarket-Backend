package com.saleshub.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.saleshub.domain.dto.CustomerNewDTO;
import com.saleshub.domain.enums.ClientType;
import com.saleshub.resources.handler.FieldMessage;
import com.saleshub.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert,CustomerNewDTO>{
	
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
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
			}
		
		return list.isEmpty();
	}

}
