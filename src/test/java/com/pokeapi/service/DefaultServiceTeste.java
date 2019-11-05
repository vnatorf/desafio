package com.pokeapi.service;

import org.dozer.DozerBeanMapper;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import com.pokeapi.exception.PokeApiException;

public abstract class DefaultServiceTeste {

	@Mock
	protected DozerBeanMapper mapper;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	protected void buildThrownExpect(final String erro) {
		this.thrown.expect(PokeApiException.class);
		this.thrown.expectMessage(erro);
	}

}