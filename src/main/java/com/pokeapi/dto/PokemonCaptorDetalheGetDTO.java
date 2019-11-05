package com.pokeapi.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "DTO mais detalhado com as informações do pokemon capturado")
public class PokemonCaptorDetalheGetDTO extends PokemonCaptorGetDTO {

	@ApiModelProperty(notes = "Lista das habilidades do pokemon capturado")
	private List<HabilidadeTheftGetDTO> habilidades;

	public List<HabilidadeTheftGetDTO> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<HabilidadeTheftGetDTO> habilidades) {
		this.habilidades = habilidades;
	}

}