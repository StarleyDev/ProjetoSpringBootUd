package com.starlley.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starlley.cursomc.domain.Categoria;
import com.starlley.cursomc.dto.CategoriaDTO;
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

	// Deletando Categoria do banco de dados //
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Monstrando todas as categorais //
	@RequestMapping(method = RequestMethod.GET)
	// PathVariable faz com que o ID va para a variavel //
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

	// Retornando paginação de categoria //
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "diretcion", defaultValue = "ASC") String direction) {

		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
