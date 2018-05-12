package com.starlley.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starlley.cursomc.domain.Pedido;
import com.starlley.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos") // endpoint //
public class PedidoResource {

	@Autowired
	private PedidoService service;

	// EndPont retornando o ID //
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) throws Throwable {

		Pedido obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

}
