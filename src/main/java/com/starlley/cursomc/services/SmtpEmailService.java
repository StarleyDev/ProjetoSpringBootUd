package com.starlley.cursomc.services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.starlley.cursomc.domain.Cliente;
import com.starlley.cursomc.domain.Pedido;

public class SmtpEmailService extends AbstractEmailService {

	// Instanciar um objeto Logger //
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	// Automaticamente o framework ira instanciar os dados do e-mail //
	@Autowired
	private MailSender mailSender;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		
		LOG.info("Enviado email...");
		// Aqui ele envia o e-mail //
		mailSender.send(msg);
		LOG.info("Email enviado!");
		

	}

}
