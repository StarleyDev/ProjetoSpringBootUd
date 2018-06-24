package com.starlley.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starlley.cursomc.domain.Cidade;

// Criando um repository para buscar as cidade//
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
