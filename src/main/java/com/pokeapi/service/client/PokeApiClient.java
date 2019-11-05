package com.pokeapi.service.client;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.pokeapi.util.ChaveApplicationPropertiesUtil;

@Service
public class PokeApiClient {

	@Autowired
	private RestOperations restOperations;

	private final String host;

	/**
	 * Inicializa a classe com o host configurado no 'application.properties' para a 
	 * API API <a href="https://pokeapi.co/">PokéAPI</a>
	 *  
	 * @param host Host configurado
	 */
	public PokeApiClient(@Value(ChaveApplicationPropertiesUtil.SERVICE.POKEAPI_HOST) final String host) {
		this.host = host;
	}

	/**
	 * Método responsável por fazer a chamada getForObject para um único registro.
	 * 
	 * @param path Path que será utlizado na montagem do serviço
	 * @param responseType Class de retorno
	 * @param uriVariables Parâmetros para consulta
	 * @return
	 */
	public <T> T get(final String path,
					 final Class<T> responseType,
					 final Object... uriVariables) {

		final StringBuilder url = new StringBuilder()
										.append(this.host)
										.append(path);

		Optional
		.ofNullable(uriVariables)
		.ifPresent(parametros -> Arrays
									.asList(parametros)
									.stream()
									.forEach(param -> url.append("/").append(param)));

		return this.restOperations.getForObject(url.toString(), responseType);
	}

	/**
	 * Método responsável por fazer a chamada getForObject para uma lista de registro com paginação.
	 * 
	 * @param path Path que será utlizado na montagem do serviço
	 * @param tamanho Máximo de registros para trazer numa única consulta
	 * @param inicio Paginação da consulta
	 * @param responseType Class de retorno
	 * @return
	 */
	public <T> T get(final String path,
					 final Integer tamanho,
					 final Integer inicio,
					 final Class<T> responseType) {

		final String limit = tamanho != null ? "?limit=" + tamanho : "";
		final String offset = inicio != null ? limit != "" ? "&offset=" + inicio : "?offset=" + inicio : "";

		final StringBuilder url = new StringBuilder()
										.append(this.host)
										.append(path)
										.append(limit)
										.append(offset);

		return this.restOperations.getForObject(url.toString(), responseType);
	}

}