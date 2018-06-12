package com.starlley.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.starlley.cursomc.domain.Categoria;
import com.starlley.cursomc.domain.Produto;

// Criando um repository para buscar os produtos //
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	// JPQL para consulta via JPA //

	// @Param - serve para pegar o valor da variavel "nome" e "categorias", para
	// jogar no JPQL

	// findDistinctByNomeContainingAndCategoriasIn -> Subistitui toda Query e retira
	// os @Param
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,
			Pageable pageRequest);

}
