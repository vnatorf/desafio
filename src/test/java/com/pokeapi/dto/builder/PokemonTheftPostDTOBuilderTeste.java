package com.pokeapi.dto.builder;

import org.mockito.Mockito;

import com.pokeapi.dto.PokemonCaptorPostDTO;

public final class PokemonTheftPostDTOBuilderTeste {

	public static final PokemonCaptorPostDTO mock() {
		return Mockito.mock(PokemonCaptorPostDTO.class);
	}

}