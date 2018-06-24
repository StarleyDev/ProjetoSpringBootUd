package com.starlley.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.starlley.cursomc.domain.Pedido;

// Interface de Email //
public interface EmailService {

	// Enviar uma confirmação de email com os pedidos //
	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
	
	// Enviar uma confirmação de email com os pedidos formatado HTML //
	void sendOrderConfirmationHtmlEmail(Pedido obj);
		
	// Metodos para o uso do thymeleaf //
	void sendHtmlEmail(MimeMessage msg);

}
