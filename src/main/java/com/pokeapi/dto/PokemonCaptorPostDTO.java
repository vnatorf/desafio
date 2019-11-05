package com.pokeapi.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.pokeapi.dto.check.PostCheck;
import com.pokeapi.util.ChaveMensagemUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "DTO onde serão informados os pokemons para captura")
public class PokemonCaptorPostDTO {

	@ApiModelProperty(required = true,
					  notes = "Lista dos pokemons que serão capturados")
	@NotNull(groups = PostCheck.class,
			 message = ChaveMensagemUtil.THEFT.PARAM_POKEMONS_NOT_NULL)
	private Set<String> pokemons;

	public Set<String> getPokemons() {
		return pokemons;
	}

	public void setPokemons(Set<String> pokemons) {
		this.pokemons = pokemons;
	}

}