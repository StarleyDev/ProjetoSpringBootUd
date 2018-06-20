package com.starlley.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.starlley.cursomc.domain.Pedido;

// Interface de Email //
public interface EmailService {

	// Enviar uma confirmação de email com os pedidos //
	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

}
