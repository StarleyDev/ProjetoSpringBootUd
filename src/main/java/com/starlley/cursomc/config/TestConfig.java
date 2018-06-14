package com.starlley.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.starlley.cursomc.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	// Criando um BEAN para iniciar o bando de dados //

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDateBase() throws ParseException {

		dbService.instantiateTestDataBase();

		return true;
	}

}
