package com.starlley.cursomc.services.exceptions;

public class ObjetcNotFoundException extends RuntimeException {

	// Classe de exeções //
	private static final long serialVersionUID = 1L;

	public ObjetcNotFoundException(String msg) {

		super(msg);
	}

	public ObjetcNotFoundException(String msg, Throwable cause) {

		super(msg, cause);
	}

}
