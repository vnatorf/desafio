package com.pokeapi.resource;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokeapi.service.AuditoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "Resource para consulta individual ou em lista das URL's auditadas pela API")
@RestController
@RequestMapping(value = "/pokeapi/auditoria")
public class AuditoriaResource {

	@Autowired
	private AuditoriaService service;

	/**
	 * Serviço responsável por chamar o método {@link com.pokeapi.service.AuditoriaService#consultar(Integer)}}
	 * 
	 * @param id Identificador da auditoria
	 * @return ResponseEntity com a auditoria
	 */
	@ApiOperation(value = "Consulta uma única auditoria", response = ResponseEntity.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@ApiParam(value = "Identificador da auditoria", required = true)
									  @PathVariable @Min(1) final Integer id) {

		return ResponseEntity.ok(this.service.consultar(id));
	}

	/**
	 * Serviço responsável por chamar o método {@link com.pokeapi.service.AuditoriaService#listar(String, LocalDate)}}
	 * 
	 * @param url URL que foi auditada
	 * @param dataConsulta Data em que foi efetuada a consulta
	 * @return ResponseEntity com a lista de auditorias
	 */
	@ApiOperation(value = "Consulta uma lista de auditorias", response = ResponseEntity.class)
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	@GetMapping
	public ResponseEntity<Object> get(@ApiParam(value = "URL que foi auditada")
									  @RequestParam(required = false, name = "url") final String url,
									  @ApiParam(value = "Data em que foi efetuada a consulta")
									  @RequestParam(required = false, name = "dataConsulta")
									  @DateTimeFormat(iso = ISO.DATE) final LocalDate dataConsulta) {

		return ResponseEntity.ok(this.service.listar(url, dataConsulta));
	}

}