package com.starlley.cursomc.dto;

import java.io.Serializable;

import com.starlley.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// Define os dados que irão retornar de um BD //
	private Integer id;
	private String nome;

	public CategoriaDTO() {

	}

	public CategoriaDTO(Categoria obj) {

		id = obj.getId();
		nome = obj.getNome();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
