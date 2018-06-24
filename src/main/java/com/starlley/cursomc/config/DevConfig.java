package com.starlley.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.starlley.cursomc.services.DBService;
import com.starlley.cursomc.services.EmailService;
import com.starlley.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	// Criando um BEAN para iniciar o bando de dados //

	@Autowired
	private DBService dbService;

	// Pegando o valor da chave da aplication-dev.properties //
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDateBase() throws ParseException {

		// Criando a condição "se-não" //
		if (!"create".equals(strategy)) {

			return false;
		}

		// irá criar o banco de dados //
		dbService.instantiateTestDataBase();

		return true;
	}

	// Bean de e-mail //
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();

	}
}
