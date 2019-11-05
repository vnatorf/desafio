package com.pokeapi.dto.builder;

import java.util.Optional;

import org.mockito.Mockito;

import com.pokeapi.model.PokemonCaptor;

public final class PokemonTheftBuilderTeste {

	public static final PokemonCaptor mock() {
		return Mockito.mock(PokemonCaptor.class);
	}

	public static final Optional<PokemonCaptor> optional() {
		return Optional.of(Mockito.mock(PokemonCaptor.class));
	}

}