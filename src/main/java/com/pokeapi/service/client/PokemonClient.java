package com.pokeapi.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pokeapi.service.client.dto.PokemonDTO;
import com.pokeapi.service.client.dto.ListaDTO;
import com.pokeapi.util.ChaveApplicationPropertiesUtil;

@Service
public class PokemonClient {

	@Autowired
	private PokeApiClient pokeApiClient;

	private final String path;

	/**
	 * Inicializa a classe com o path configurado no 'application.properties'
	 *  
	 * @param path Path configurado para o Pokemon
	 */
	public PokemonClient(@Value(ChaveApplicationPropertiesUtil.SERVICE.POKEAPI_POKEMON_PATH) final String path) {
		this.path = path;
	}

	/**
	 * Método responsável por chamar a pokeApiClient {@link com.pokeapi.service.client.PokeApiClient#get(String, Class, Object...)}}
	 * 
	 * @param idOrName Identificador ou nome do pokemon
	 * @return Retorna o PokemonDTO
	 */
	public PokemonDTO get(final String idOrName) {
		return this.pokeApiClient.get(this.path, PokemonDTO.class, idOrName);
	}

	/**
	 * Método responsável por chamar a pokeApiClient {@link com.pokeapi.service.client.PokeApiClient#get(String, Integer, Integer, Class)}}
	 * 
	 * @param tamanho Máximo de registros para trazer numa única consulta
	 * @param inicio Paginação da consulta
	 * @return Retorna o ListaDTO
	 */
	public ListaDTO get(final Integer tamanho, final Integer inicio) {
		return this.pokeApiClient.get(this.path, tamanho, inicio, ListaDTO.class);
	}

}