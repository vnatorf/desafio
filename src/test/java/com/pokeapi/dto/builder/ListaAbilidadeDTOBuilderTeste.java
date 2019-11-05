package com.pokeapi.dto.builder;

import org.mockito.Mockito;

import com.pokeapi.service.client.dto.ListaAbilidadeDTO;

public class ListaAbilidadeDTOBuilderTeste {

	public static final ListaAbilidadeDTO mock() {
		return Mockito.mock(ListaAbilidadeDTO.class);
	}

}
