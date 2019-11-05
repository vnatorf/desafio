package com.pokeapi.exception;

import java.util.Arrays;
import java.util.List;

public class PokeApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<Object> parametros;

	public PokeApiException() {
		super();
	}

	public PokeApiException(final String message, final Object... parametros) {
		super(message);
		this.parametros = Arrays.asList(parametros);
	}

	public PokeApiException(final String message) {
		super(message);
	}

	public PokeApiException(final Throwable cause) {
		super(cause);
	}

	public List<Object> getParametros() {
		return parametros;
	}

	public void setParametros(List<Object> parametros) {
		this.parametros = parametros;
	}

}