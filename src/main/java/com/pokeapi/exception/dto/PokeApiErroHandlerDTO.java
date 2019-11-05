package com.pokeapi.exception.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class PokeApiErroHandlerDTO {

	private HttpStatus status;

	private List<String> detalhe;

	public static synchronized PokeApiErroHandlerDTO create() {
		return new PokeApiErroHandlerDTO();
	}

	public PokeApiErroHandlerDTO withStatus(final HttpStatus status) {
		this.status = status;
		return this;
	}

	public PokeApiErroHandlerDTO withDetalhe(final String detalhe) {
		this.detalhe = Arrays.asList(detalhe);
		return this;
	}

	public PokeApiErroHandlerDTO withDetalhe(final List<String> detalhe) {
		this.detalhe = detalhe;
		return this;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<String> getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(List<String> detalhe) {
		this.detalhe = detalhe;
	}

}