package com.pokeapi.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "DTO mais detalhado com as informações do pokemon capturado")
public class PokemonCaptorDetalheGetDTO extends PokemonCaptorGetDTO {

	@ApiModelProperty(notes = "Lista das abilidades do pokemon capturado")
	private List<AbilidadeTheftGetDTO> abilidades;

	public List<AbilidadeTheftGetDTO> getAbilidades() {
		return abilidades;
	}

	public void setAbilidades(List<AbilidadeTheftGetDTO> abilidades) {
		this.abilidades = abilidades;
	}

}