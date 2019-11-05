package com.pokeapi.dto.builder;

import org.mockito.Mockito;

import com.pokeapi.service.client.dto.AbilidadeDTO;

public class AbilidadeDTOBuilderTeste {

	public static final AbilidadeDTO mock() {
		return Mockito.mock(AbilidadeDTO.class);
	}

}