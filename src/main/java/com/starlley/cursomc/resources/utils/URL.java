package com.starlley.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	// Retorna um paramentro sem espaços //
	public static String decodeParam(String s) {

		try {
			return URLDecoder.decode(s, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// Caso de erro, ira retornar uma string vazia //
			return "";
		}

	}

	// Ira percorrer o endpoint //
	public static List<Integer> decodeIntList(String s) {

		String[] vet = s.split(",");

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < vet.length; i++) {

			list.add(Integer.parseInt(vet[i]));

		}

		return list;

		// O codigo abaixo faz a mesma coisa do codigo acima //
		// return Arrays.asList(s.split(",")).stream().map(x ->
		// Integer.parseInt(x)).collect(Collectors.toList());
	}

}
