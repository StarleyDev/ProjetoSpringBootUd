package com.starlley.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starlley.cursomc.domain.Cliente;
import com.starlley.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes") // endpoint //
public class ClienteResource {

	@Autowired
	private ClienteService service;

	// EndPont retornando o ID //
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) throws Throwable {

		Cliente obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

}
