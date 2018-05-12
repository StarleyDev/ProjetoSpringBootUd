package com.starlley.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starlley.cursomc.domain.Categoria;
import com.starlley.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias") // endpoint //
public class CategoriaResource {

	@Autowired // Instancia automaticamente //
	private CategoriaService service;

	// EndPont retornando o ID //
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// PathVariable faz com que o ID va para a variavel //
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws Throwable {

		Categoria obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

	// Inserindo a categoria no banco de dados //
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		// Pegando a URI do novo recurso para retornar uma nova resposta //
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// Gerando codigo 201 "Created" //
		return ResponseEntity.created(uri).build();
	}

	// Atualizando a categoria no banco de dados //
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();

	}
}
