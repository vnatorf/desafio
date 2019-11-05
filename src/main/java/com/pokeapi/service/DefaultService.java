package com.pokeapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.pokeapi.exception.PokeApiException;
import com.pokeapi.util.PokeApiUtil;

public abstract class DefaultService {

	@Autowired
	private DozerBeanMapper mapper;

	/**
	 * Método responsável por validar a expressão enviada e em caso de false retornar uma Exception
	 * com sua respectiva mensagem.
	 * 
	 * @param expression Expressão para validação
	 * @param chave Chave da mensagem em caso de exceção
	 * @param parametros Parâmetros para compor a mensagem de exceção
	 */
	public static void assertTrue(final boolean expression,
								  final String chave,
								  final Object... parametros) {

		if (!expression) {
			throw new PokeApiException(chave, parametros);
		}
	}

	/**
	 * Método responsável por validar a expressão enviada e em caso de verdadeiro retornar uma Exception
	 * com sua respectiva mensagem.
	 * 
	 * @param expression Expressão para validação
	 * @param chave Chave da mensagem em caso de exceção
	 * @param parametros Parâmetros para compor a mensagem de exceção
	 */
	public static void assertFalse(final boolean expression,
								   final String chave,
								   final Object... parametros) {

		if (expression) {
			throw new PokeApiException(chave, parametros);
		}
	}

	/**
	 * Método responsável por fazer o mapeamento da entidade do 'source', no caso um Optional, para a classe de destino 'destinationClass'.
	 * 
	 * @param source Objeto de origem do mapeamento
	 * @param destinationClass Classe de destino do mapeamento
	 * @return Objeto convestido na classe desejada
	 */
	protected <T> T map(final Optional<?> source, final Class<T> destinationClass) {
		if (source == null || !source.isPresent()) {
			return null;
		}

		return this.mapper.map(source.get(), destinationClass);
	}

	/**
	 * Método responsável por fazer o mapeamento da entidade do 'source' para a classe de destino 'destinationClass'.
	 * 
	 * @param source Objeto de origem do mapeamento
	 * @param destinationClass Classe de destino do mapeamento
	 * @return Objeto convestido na classe desejada
	 */
	protected <T> T map(final Object source, final Class<T> destinationClass) {
		if (source == null) {
			return null;
		}

		return this.mapper.map(source, destinationClass);
	}

	/**
	 * Método responsável por fazer o mapeamento deu uma lista entidades do 'sources' para a classe de destino 'destinationClass'.
	 * 
	 * @param sources Objetos de origem do mapeamento
	 * @param destinationClass Classe de destino do mapeamento
	 * @return Lista de objetos convestidos na classe desejada
	 */
	protected <T> List<T> map(List<?> sources, Class<T> destinationClass) {
		return PokeApiUtil
				.emptyIfNull(sources)
				.parallelStream()
				.map(source -> this.map(source, destinationClass))
				.collect(Collectors.toList());
	}

}