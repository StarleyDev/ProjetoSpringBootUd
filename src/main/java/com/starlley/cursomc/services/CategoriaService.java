package com.starlley.cursomc.services;

import java.util.List;
import java.util.Optional; // Obrigatorio no Java 8 //

import org.springframework.dao.DataIntegrityViolationException;

import com.starlley.cursomc.services.exceptions.DataIntegrityException;
import com.starlley.cursomc.services.exceptions.ObjetcNotFoundException;
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

	// Criando um metodo de busca e verifica se ele existe //
	public Categoria find(Integer id) {

		Optional<Categoria> obj = repo.findById(id); // Realizando busca no banco de dados //

		// Se o objeto for encontrado ele e instanciado, se não ele retorna a msg //
		return obj.orElseThrow(() -> new ObjetcNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	// Metodo de inserir uma categoria //
	public Categoria insert(Categoria obj) {

		// Objeto novo deve ter o id nulo //
		obj.setId(null);

		return repo.save(obj);

	}

	// Metodo de atualização de categoria //
	public Categoria update(Categoria obj) {

		// Objeto para verificar //
		find(obj.getId());

		return repo.save(obj);
	}

	// Metodo de deletar //
	public void delete(Integer id) {

		find(id);

		try {
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não e possivel excluir uma categoria que possui produtos!");
		}

	}

	// Mostrando todas as categorias //
	public List<Categoria> findAll() {

		return repo.findAll();

	}
}
