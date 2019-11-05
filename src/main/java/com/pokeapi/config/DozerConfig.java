package com.pokeapi.config;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

	/**
	 * Método responsável por inicializar a configuração do dozer.
	 * 
	 * @return Retorna DozerBeanMapper configurado
	 */
	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		final DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(Arrays.asList("dozer-mapping.xml"));
		return dozerBean;
	}

}