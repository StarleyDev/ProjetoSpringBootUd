package com.starlley.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.starlley.cursomc.domain.Cliente;
import com.starlley.cursomc.dto.ClienteDTO;
import com.starlley.cursomc.repositories.ClienteRepository;
import com.starlley.cursomc.resources.exceptions.FieldMessage;

// Classe para tratamento de erro e validação //

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;

	// Manter o repositorio do cliente //
	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		

		// O Map ira servir para buscar o codigo da URI //
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer urlId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();
		
		// Validação de comparação de e-mail //
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if (aux != null && !aux.getId().equals(urlId)) {

			list.add(new FieldMessage("email", "Email já cadastrado!"));

		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
