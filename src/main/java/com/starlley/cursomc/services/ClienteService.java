package com.starlley.cursomc.services;

import java.util.Optional;
import com.starlley.cursomc.services.exceptions.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starlley.cursomc.domain.Cliente;
import com.starlley.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	// Buscando uma categoria por codigo //

	// Declarando uma dependencia //
	@Autowired
	private ClienteRepository repo;

	// Criando um metodo de busca //
	public Cliente find(Integer id) {

		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjetcNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

}
