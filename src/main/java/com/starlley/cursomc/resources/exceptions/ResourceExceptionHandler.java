package com.starlley.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.starlley.cursomc.services.exceptions.DataIntegrityException;
import com.starlley.cursomc.services.exceptions.ObjetcNotFoundException;

// Criando pagina de tratamento de erro //
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetcNotFoundException.class) // Tratador de exeção //
	public ResponseEntity<StandardError> objectNotFound(ObjetcNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	@ExceptionHandler(DataIntegrityException.class) // Tratador de exeção ao tentar excluir com dependencia //
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class) // Tratador de exeção ao tentar validar //
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação!",
				System.currentTimeMillis());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {

			err.addError(x.getField(), x.getDefaultMessage());

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
