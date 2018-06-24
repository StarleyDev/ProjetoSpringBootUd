package com.starlley.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.starlley.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	// Pegando uma string na aplication //
	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngene;

	// Serve para instanciar o MimeMessage //
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {

		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);

	}

	// Corpo do email //
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {

		SimpleMailMessage sm = new SimpleMailMessage();

		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Codigo: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());

		return sm;
	}

	// Metodo é responsavel por retornar HTML com os dados //
	protected String htmlFormTemplatePedido(Pedido obj) {

		Context ctx = new Context();

		// Ingetando o pedido dentro do template //
		ctx.setVariable("pedido", obj);

		// Necessário colocar o caminho do HTML //
		return templateEngene.process("email/confirmacaoPedido", ctx);

	}

	// Enviando confirmação com novo formato em HTML //
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {

		// Ira tentar enviar o email com formato HTML //
		try {
		MimeMessage mm = prepareMimeMessageFromPedido(obj);
		sendHtmlEmail(mm);
		
		// Caso não de certo, ira mandar no formato txt //
		}catch(MessagingException ex) {
			
			sendOrderConfirmationEmail(obj);			
		}
		
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {

		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		// Corpo do mmh //
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFormTemplatePedido(obj), true);

		return mm;
	}
}
