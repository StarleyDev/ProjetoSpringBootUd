package com.starlley.cursomc.services;

import java.util.Optional; // Obrigatorio no Java 8 //

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.starlley.cursomc.domain.Categoria;
import com.starlley.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	// Buscando uma categoria por codigo //

	// Declarando uma dependencia //
	@Autowired
	private CategoriaRepository repo;

	// Criando um metodo de busca //
	public Categoria buscar(Integer id) {

		Optional<Categoria> obj = repo.findById(id); // Realizando busca no banco de dados //

		// Se o objeto for encontrado ele e instanciado, se não ele retorna nulo //
		return obj.orElse(null);

	}

}
