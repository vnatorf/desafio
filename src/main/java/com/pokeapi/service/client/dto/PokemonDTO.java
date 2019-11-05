package com.pokeapi.service.client.dto;

import java.util.List;

public class PokemonDTO {

	private Integer id;

	private String name;

	private Integer height;

	private Integer weight;

	private List<ListaHabilidadeDTO> abilities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public List<ListaHabilidadeDTO> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<ListaHabilidadeDTO> abilities) {
		this.abilities = abilities;
	}

}