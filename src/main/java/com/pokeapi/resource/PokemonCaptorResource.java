package com.pokeapi.resource;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokeapi.dto.PokemonCaptorPostDTO;
import com.pokeapi.dto.check.PostCheck;
import com.pokeapi.service.PokemonCaptorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "Resource para para captura, exclusão e consulta de pokemons")
@RestController
@RequestMapping(value = "/pokeapi/pokemon/captor")
public class PokemonCaptorResource {

	@Autowired
	private PokemonCaptorService service;

	/**
	 * Serviço responsável por chamar o método {@link com.pokeapi.service.PokemonCaptorService#capturar(PokemonCaptorPostDTO)}}
	 * 
	 * @param dto DTO onde será informada a lista de pokemons para captura
	 * @return ResponseEntity com o pokemon
	 */
	@ApiOperation(value = "Captura os pokemons selecionados", response = ResponseEntity.class)
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	@ApiResponses(value = {
		@ApiResponse(code = 404, message = "Página não encontrada na API de consulta"),
		@ApiResponse(code = 400, message = "Erro de négócio, o mesmo será acompanhado da mensagem com a exceção"),
	})
	@PostMapping
	public ResponseEntity<Object> post(@ApiParam(value = "DTO onde será informada a lista de pokemons para captura", required = true)
									   @Validated(PostCheck.class)
									   @RequestBody final PokemonCaptorPostDTO dto) {

		return ResponseEntity.ok(this.service.capturar(dto));
	}

	/**
	 * Serviço responsável por chamar o método {@link com.pokeapi.service.PokemonCaptorService#excluir(Integer)}}
	 * 
	 * @param id Identificador do pokemon capturado
	 * @return ResponseEntity com o código de sucesso ou não
	 */
	@ApiOperation(value = "Deleta o pokemon capturado", response = ResponseEntity.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces="application/json")
	@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Erro de négócio, o mesmo será acompanhado da mensagem com a exceção")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@ApiParam(value = "Identificador do pokemon capturado", required = true)
										 @PathVariable @Min(1) final Integer id) {

		this.service.excluir(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Serviço responsável por chamar o método {@link com.pokeapi.service.PokemonCaptorService#consultar(Integer)}}
	 * 
	 * @param id Identificador do pokemon capturado
	 * @return ResponseEntity com o pokemon capturado
	 */
	@ApiOperation(value = "Consulta um único pokemon capturado", response = ResponseEntity.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@ApiParam(value = "Identificador do pokemon capturado", required = true)
									  @PathVariable @Min(1) final Integer id) {

		return ResponseEntity.ok(this.service.consultar(id));
	}

	/**
	 * Serviço responsável por chamar o método {@link com.pokeapi.service.PokemonCaptorService#listar(String, Integer, LocalDate)}}
	 * 
	 * @param nomePokemon Nome do pokemon
	 * @param identificador Identificador do pokemon capturado
	 * @param dataEntradaSistema Data da capturada do pokemon
	 * @return ResponseEntity com a lista de pokemons capturados
	 */
	@ApiOperation(value = "Consulta uma lista de pokemons capturados", response = ResponseEntity.class)
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	@GetMapping
	public ResponseEntity<Object> get(@ApiParam(value = "Nome do pokemon")
									  @RequestParam(required = false, name = "nomePokemon") final String nomePokemon,
									  @ApiParam(value = "Identificador do pokemon capturado")
									  @RequestParam(required = false, name = "identificador") final Integer identificador,
									  @ApiParam(value = "Data da capturada do pokemon")
									  @RequestParam(required = false, name = "dataEntradaSistema")
									  @DateTimeFormat(iso = ISO.DATE) final LocalDate dataEntradaSistema) {

		return ResponseEntity.ok(this.service.listar(nomePokemon, identificador, dataEntradaSistema));
	}

}