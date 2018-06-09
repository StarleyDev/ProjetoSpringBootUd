package com.starlley.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.starlley.cursomc.services.exceptions.DataIntegrityException;
import com.starlley.cursomc.services.exceptions.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.starlley.cursomc.domain.Cliente;
import com.starlley.cursomc.dto.ClienteDTO;
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

	// Metodo de atualização de Cliente //
	public Cliente update(Cliente obj) {

		// Será buscado no bando de dados e depois salvar a modificação //
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);

		return repo.save(newObj);
	}

	// Metodo de deletar //
	public void delete(Integer id) {

		find(id);

		try {
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não e possivel excluir um Cliente que possui pedidos!");
		}

	}

	// Mostrando todas as Clientes //
	public List<Cliente> findAll() {

		return repo.findAll();
	}

	// Função de retorno de paginação //
	@SuppressWarnings("deprecation")
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	// Metodo auxiliar //
	public Cliente fromDTO(ClienteDTO objDto) {

		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);

	}

	// Somente será realizado o update //
	private void updateData(Cliente newObj, Cliente obj) {

		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

}
