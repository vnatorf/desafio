package com.pokeapi.dto.builder;

import org.mockito.Mockito;

import com.pokeapi.service.client.dto.HabilidadeDTO;

public class HabilidadeDTOBuilderTeste {

	public static final HabilidadeDTO mock() {
		return Mockito.mock(HabilidadeDTO.class);
	}

}