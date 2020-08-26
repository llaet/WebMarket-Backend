package com.saleshub.config;

import com.saleshub.services.EmailService;
import com.saleshub.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.saleshub.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dbGenerationStrategy;
	
	@Bean
	public Boolean instantiateDatabase() throws Exception {
		
		if(!dbGenerationStrategy.equals("create")) {
			return false;
		}
		
		this.dbService.instantiateDatabase();
		
		return true;
	}

	@Bean
	public EmailService getEmailService(){
		return new SmtpEmailService();
	}
}
