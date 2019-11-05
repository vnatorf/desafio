package com.pokeapi.dto;

import java.time.LocalDate;

import org.dozer.Mapping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "DTO com as informações mais básicas do pokemon capturado")
public class PokemonCaptorGetDTO {

	@ApiModelProperty(notes = "Identificador do pokemon capturado")
	@Mapping("id")
	private Integer identificador;

	@ApiModelProperty(notes = "Nome do pokemon capturado")
	@Mapping("dsNom")
	private String nome;

	@ApiModelProperty(notes = "Altura do pokemon capturado")
	@Mapping("nrHei")
	private Integer altura;

	@ApiModelProperty(notes = "Peso do pokemon capturado")
	@Mapping("nrWei")
	private Integer peso;

	@ApiModelProperty(notes = "Data da captura do pokemon")
	@Mapping("dtEntSis")
	private LocalDate dataEntradaSistema;

	public static synchronized PokemonCaptorGetDTO create() {
		return new PokemonCaptorGetDTO();
	}

	public PokemonCaptorGetDTO withIdentificador(final Integer identificador) {
		this.identificador = identificador;
		return this;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public LocalDate getDataEntradaSistema() {
		return dataEntradaSistema;
	}

	public void setDataEntradaSistema(LocalDate dataEntradaSistema) {
		this.dataEntradaSistema = dataEntradaSistema;
	}

}