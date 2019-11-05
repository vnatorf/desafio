package com.pokeapi.resource;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokeapi.service.PokemonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "Resource para consulta individual ou em lista nos pokemons - https://pokeapi.co/docs/v2.html/#pokemon-section")
@RestController
@RequestMapping(value = "/pokeapi/pokemon")
public class PokemonResource {

	@Autowired
	private PokemonService service;

	/**
	 * Serviço responsável por chamar o método {@link com.pokeapi.service.PokemonService#get(String)}}
	 * 
	 * @param idOrName Identificador ou nome do pokemon
	 * @return ResponseEntity com o pokemon
	 */
	@ApiOperation(value = "Consulta um único pokemon", response = ResponseEntity.class)
	@RequestMapping(value = "/{idOrName}", method = RequestMethod.GET, produces="application/json")
	@ApiResponses(value = {
		@ApiResponse(code = 404, message = "Página não encontrada na API de consulta")
	})
	@GetMapping("/{idOrName}")
	public ResponseEntity<Object> get(@ApiParam(value = "Identificador ou nome do pokemon", required = true)
									  @PathVariable @Min(1) final String idOrName) {

		return ResponseEntity.ok(this.service.get(idOrName));
	}

	/**
	 * Serviço responsável por chamar o método {@link com.pokeapi.service.PokemonService#get(Integer, Integer)}}
	 * 
	 * @param tamanho Máximo de registros para trazer numa única consulta
	 * @param inicio Paginação da consulta
	 * @return ResponseEntity com a lista de pokemons
	 */
	@ApiOperation(value = "Consulta uma lista de pokemons", response = ResponseEntity.class)
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	@GetMapping
	public ResponseEntity<Object> get(@ApiParam(value = "Máximo de registros para trazer numa única consulta")
									  @RequestParam(required = false, name = "tamanho") final Integer tamanho,
									  @ApiParam(value = "Paginação da consulta")
									  @RequestParam(required = false, name = "inicio") final Integer inicio) {

		return ResponseEntity.ok(this.service.get(tamanho, inicio));
	}

}