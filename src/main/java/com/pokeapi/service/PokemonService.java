package com.pokeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokeapi.service.client.PokemonClient;
import com.pokeapi.service.client.dto.PokemonDTO;
import com.pokeapi.service.client.dto.ListaDTO;

@Service
public class PokemonService {

	@Autowired
	private PokemonClient client;

	/**
	 * Método responsável por efetuar a chamada do client de pokemon para consultas individuais
	 * 
	 * @param idOrName Identificador ou nome do pokemon desejado
	 * @return Retorna o pokemon selecionado
	 */
	public PokemonDTO get(final String idOrName) {
		return this.client.get(idOrName);
	}

	/**
	 * Método responsável por efetuar a chamada do client de pokemon para listagem
	 * 
	 * @param tamanho Limite da listagem
	 * @param inicio Paginação da listagem
	 * @return Retorna uma lista pokemons
	 */
	public ListaDTO get(final Integer tamanho, final Integer inicio) {
		return this.client.get(tamanho, inicio);
	}

}