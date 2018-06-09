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
import org.springframework.transaction.annotation.Transactional;

import com.starlley.cursomc.domain.Cidade;
import com.starlley.cursomc.domain.Cliente;
import com.starlley.cursomc.domain.Endereco;
import com.starlley.cursomc.domain.enums.TipoCliente;
import com.starlley.cursomc.dto.ClienteDTO;
import com.starlley.cursomc.dto.ClienteNewDTO;
import com.starlley.cursomc.repositories.ClienteRepository;
import com.starlley.cursomc.repositories.EnderecoRepository;

@Service
public class ClienteService {

	// Buscando uma categoria por codigo //

	// Declarando uma dependencia //
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;

	// Criando um metodo de busca //
	public Cliente find(Integer id) {

		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjetcNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;

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

	public Cliente fromDTO(ClienteNewDTO objDto) {

		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;

	}

	// Somente será realizado o update //
	private void updateData(Cliente newObj, Cliente obj) {

		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

}
