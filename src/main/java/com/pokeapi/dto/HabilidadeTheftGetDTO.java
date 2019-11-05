package com.pokeapi.dto;

import org.dozer.Mapping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "DTO com as informações da habilidade do pokemon")
public class HabilidadeTheftGetDTO {

	@ApiModelProperty(notes = "Nome da habilidade")
	@Mapping("dsNom")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}