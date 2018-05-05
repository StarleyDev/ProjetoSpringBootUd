package com.starlley.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starlley.cursomc.domain.ItemPedido;

// Criando um repository para buscar os produtos //
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
