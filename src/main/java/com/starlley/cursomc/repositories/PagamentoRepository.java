package com.starlley.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starlley.cursomc.domain.Pagamento;

// Criando um repository para buscar as categorias //
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
