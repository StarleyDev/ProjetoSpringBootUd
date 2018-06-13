package com.starlley.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.starlley.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	// Classe para calcular data de vencimento com boleto com data definida em 7
	// dias //
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {

		// Instanciar um calendario pegando o instante //
		Calendar cal = Calendar.getInstance();
		// Atribuindo a variavel //
		cal.setTime(instanteDoPedido);
		// Adiconando 7 dias //
		cal.add(Calendar.DAY_OF_MONTH, 7);
		// Atribuindo a data para pagamento //
		pagto.setDataPagamento(cal.getTime());

	}
}
