package com.pokeapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;
import com.pokeapi.dto.AbilidadeTheftGetDTO;
import com.pokeapi.dto.AuditoriaGetDTO;
import com.pokeapi.dto.PokemonCaptorDetalheGetDTO;
import com.pokeapi.dto.PokemonCaptorGetDTO;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private TypeResolver typeResolver;

	/**
	 * Método responsável por inicializar as configurações do swagger.
	 * 
	 * Configurações:
	 * 	- Package das controllers
	 *  - Mensagens de Retorno
	 *  - Informações básicas da API
	 *  - Models expostas pelos serviços
	 * 
	 * @return Retorna o docket configurado
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.pokeapi.resource"))
					.paths(PathSelectors.any())
					.build()
					.useDefaultResponseMessages(false)
					.apiInfo(this.buildInfoAPI())
					.additionalModels(this.typeResolver.resolve(AbilidadeTheftGetDTO.class))
					.additionalModels(this.typeResolver.resolve(AuditoriaGetDTO.class))
					.additionalModels(this.typeResolver.resolve(PokemonCaptorDetalheGetDTO.class))
					.additionalModels(this.typeResolver.resolve(PokemonCaptorGetDTO.class));
	}

	/**
	 * Método responsável por montar as informações básicas da API para visualização no swagger.
	 * @return ApiInfo configurada
	 */
	private ApiInfo buildInfoAPI() {
		return new ApiInfoBuilder()
					.title("POKEMON REST API")
					.description("Consumo das informações da API https://pokeapi.co/")
					.version("0.0.1-SNAPSHOT")
					.build();
	}

}