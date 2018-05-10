package com.starlley.cursomc.services;

import java.util.Optional;
import com.starlley.cursomc.services.exceptions.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starlley.cursomc.domain.Pedido;
import com.starlley.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	// Buscando uma categoria por codigo //

	// Declarando uma dependencia //
	@Autowired
	private PedidoRepository repo;

	// Criando um metodo de busca //
	public Pedido find(Integer id) {

		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjetcNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}

}
