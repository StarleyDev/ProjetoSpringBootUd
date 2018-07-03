package com.starlley.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mysql.fabric.xmlrpc.base.Array;

@Configuration // Difinindo classe de configuração //
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Configuração para ter acesso ao H2-console
	@Autowired
	private Environment env;

	// Definindo caminhos liberados //
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	// Definindo caminhos liberados para recuperar dados//
	private static final String[] PUBLIC_MATCHERS_GET = { "/produtos/**", "/categorias/**", };

	// Sobrescrevendo o metodo configure do security //
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Configuração de acesso para H2-console via profile //
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.cors().and().csrf().disable();
		// Aqui ira deixar acesso publico que estiver na string PUBLIC_MATCHERS, e os
		// demais terão que ser autenticados //
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
		// Configuração para back-end não criar usuarios //
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	//
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}
