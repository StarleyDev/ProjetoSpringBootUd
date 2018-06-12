package com.starlley.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.starlley.cursomc.domain.Cliente;

// Criando um repository para buscar as cidade//
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	// @Transactional(readOnly=true) serve pra somente realizar leitura //
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
