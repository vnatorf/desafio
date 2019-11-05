package com.pokeapi.component;

import javax.annotation.PostConstruct;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MensagensSource {

	private MessageSourceAccessor accessor;

	/**
	 * Método responsável por montar o pesquisador de mensagens utlizados pelo sistema.
	 */
	@PostConstruct
	public void init() {
		final ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:messages");
		source.setDefaultEncoding("UTF-8");
		this.accessor = new MessageSourceAccessor(source);
	}

	/**
	 * Método responsável por buscar a mensagem no arquivo 'messages.properties' à partir de sua chave.
	 * 
	 * @param chave Chave da mensagem
	 * @return Mensagem selecionada
	 */
	public String getMessagem(final String chave) {
		return this.accessor.getMessage(chave);
	}

}