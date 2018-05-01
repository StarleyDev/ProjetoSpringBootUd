package com.starlley.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starlley.cursomc.domain.Cliente;

// Criando um repository para buscar as cidade//
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
