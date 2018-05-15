package com.starlley.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	// Classe de exeções //
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {

		super(msg);
	}

	public DataIntegrityException(String msg, Throwable cause) {

		super(msg, cause);
	}

}
