package com.saleshub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saleshub.domain.BankSlipPayment;
import com.saleshub.domain.CreditCardPayment;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class JacksonConfig {

	/*
 	*  https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-
 	*  without-hinting-the-pare
 	*/
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(CreditCardPayment.class);
				objectMapper.registerSubtypes(BankSlipPayment.class);
				super.configure(objectMapper);
			};
		};
		return builder;
	}

}
