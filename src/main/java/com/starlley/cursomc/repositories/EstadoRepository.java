package com.starlley.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starlley.cursomc.domain.Estado;

// Criando um repository para buscar as estado //
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
