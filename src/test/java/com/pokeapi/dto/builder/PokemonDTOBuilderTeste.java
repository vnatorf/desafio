package com.pokeapi.dto.builder;

import org.mockito.Mockito;

import com.pokeapi.service.client.dto.PokemonDTO;

public final class PokemonDTOBuilderTeste {

	public static final PokemonDTO mock() {
		return Mockito.mock(PokemonDTO.class);
	}

}