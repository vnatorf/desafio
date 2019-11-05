package com.pokeapi.service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.pokeapi.service.PokemonService;
import com.pokeapi.service.client.PokemonClient;

@RunWith(SpringRunner.class)
public class PokemonServiceTeste {

	@InjectMocks
	private PokemonService service;

	@Mock
	private PokemonClient client;

	@Test
	public void getSingle_withoutParameters() {
		this.service.get(null);
		verify(this.client).get(null);
	}

	@Test
	public void getSingle_withAllParameters() {
		final String parametro = "teste1";
		this.service.get(parametro);
		verify(this.client).get(parametro);
	}

	@Test
	public void getLista_withoutParameters() {
		this.service.get(null, null);

		verify(this.client).get(null, null);
	}

	@Test
	public void getLista_withTamanho() {
		final Integer tamanho = 1;

		this.service.get(tamanho, null);

		verify(this.client).get(tamanho, null);
	}

	@Test
	public void getLista_withInicio() {
		final Integer inicio = 1;

		this.service.get(null, inicio);

		verify(this.client).get(null, inicio);
	}

	@Test
	public void getLista_withAllParameters() {
		final Integer tamanho = 1;
		final Integer inicio = 1;

		this.service.get(tamanho, inicio);

		verify(this.client).get(tamanho, inicio);
	}

}