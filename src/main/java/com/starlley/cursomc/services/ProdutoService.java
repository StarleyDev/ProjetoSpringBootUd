package com.starlley.cursomc.services;

import java.util.List;
import java.util.Optional;
import com.starlley.cursomc.services.exceptions.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.starlley.cursomc.domain.Categoria;
import com.starlley.cursomc.domain.Produto;
import com.starlley.cursomc.repositories.CategoriaRepository;
import com.starlley.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	// Buscando uma categoria por codigo //

	// Declarando uma dependencia //
	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepository;

	// Criando um metodo de busca //
	public Produto find(Integer id) {

		Optional<Produto> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjetcNotFoundException(
				"Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));

	}

	// Busca Paginada //
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = categoriaRepository.findAllById(ids);

		return repo.search(nome, categorias, pageRequest);
	}

}
