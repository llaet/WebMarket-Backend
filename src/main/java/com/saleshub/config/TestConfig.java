package com.saleshub.config;

import com.saleshub.services.EmailService;
import com.saleshub.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.saleshub.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public Boolean instantiateDatabase() throws Exception {
		
		this.dbService.instantiateDatabase();
		
		return true;
	}

	@Bean
	public EmailService getEmailService(){
		return new MockEmailService();
	}

}
