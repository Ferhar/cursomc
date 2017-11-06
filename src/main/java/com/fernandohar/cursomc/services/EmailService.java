package com.fernandohar.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.fernandohar.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
