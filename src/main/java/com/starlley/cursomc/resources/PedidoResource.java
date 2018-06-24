package com.starlley.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	// Inserindo o pedido no banco de dados //
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		// Pegando a URI do novo recurso para retornar uma nova resposta //
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// Gerando codigo 201 "Created" //
		return ResponseEntity.created(uri).build();

	}
}
