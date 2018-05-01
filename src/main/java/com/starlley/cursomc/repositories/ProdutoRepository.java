package com.starlley.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starlley.cursomc.domain.Produto;

// Criando um repository para buscar os produtos //
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
