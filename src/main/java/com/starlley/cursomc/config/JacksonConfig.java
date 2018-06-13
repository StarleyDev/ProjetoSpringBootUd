package com.starlley.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starlley.cursomc.domain.PagamentoComBoleto;
import com.starlley.cursomc.domain.PagamentoComCartao;

@Configuration
public class JacksonConfig {

	// Codigo padrão para biblioteca do Jackson, para registrar os subtipos de
	// pagamento //

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {

			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}

}
