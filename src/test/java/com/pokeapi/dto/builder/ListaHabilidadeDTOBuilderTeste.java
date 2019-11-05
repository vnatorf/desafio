package com.pokeapi.dto.builder;

import org.mockito.Mockito;

import com.pokeapi.service.client.dto.ListaHabilidadeDTO;

public class ListaHabilidadeDTOBuilderTeste {

	public static final ListaHabilidadeDTO mock() {
		return Mockito.mock(ListaHabilidadeDTO.class);
	}

}
