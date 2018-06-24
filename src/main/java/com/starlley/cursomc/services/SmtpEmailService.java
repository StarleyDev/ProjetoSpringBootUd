package com.starlley.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	// Instanciar um objeto Logger //
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	// Automaticamente o framework ira instanciar os dados do e-mail //
	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(SimpleMailMessage msg) {

		LOG.info("Enviado email...");
		// Aqui ele envia o e-mail //
		mailSender.send(msg);
		LOG.info("Email enviado!");

	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {

		LOG.info("Enviado email HTML...");
		// Aqui ele envia o e-mail //
		javaMailSender.send(msg);
		LOG.info("Email enviado!");

	}

}
