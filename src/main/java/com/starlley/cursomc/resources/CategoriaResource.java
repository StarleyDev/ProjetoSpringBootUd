package com.starlley.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.starlley.cursomc.domain.Categoria;
import com.starlley.cursomc.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/categorias") // endpoint //
public class CategoriaResource {

	@Autowired // Instancia automaticamente //
	private CategoriaService service;

	// EndPont retornando o ID //
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// PathVariable faz com que o ID va para a variavel //
	public ResponseEntity<?> find(@PathVariable Integer id) throws Throwable {

		Categoria obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

}
