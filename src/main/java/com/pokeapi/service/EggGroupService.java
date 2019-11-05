package com.pokeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokeapi.service.client.EggGroupClient;
import com.pokeapi.service.client.dto.EggGroupDTO;
import com.pokeapi.service.client.dto.ListaDTO;

@Service
public class EggGroupService {

	@Autowired
	private EggGroupClient client;

	/**
	 * Método responsável por efetuar a chamada do client de eggGroup para consultas individuais
	 * 
	 * @param idOrName Identificador ou nome do eggGroup desejado
	 * @return Retorna o eggGroup selecionado
	 */
	public EggGroupDTO get(final String idOrName) {
		return this.client.get(idOrName);
	}

	/**
	 * Método responsável por efetuar a chamada do client de eggGroup para listagem
	 * 
	 * @param tamanho Limite da listagem
	 * @param inicio Paginação da listagem
	 * @return Retorna uma lista eggGroup
	 */
	public ListaDTO get(final Integer tamanho, final Integer inicio) {
		return this.client.get(tamanho, inicio);
	}

}