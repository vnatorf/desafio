package com.pokeapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_haca")
public class HabilidadeCaptor extends DefaultModel {

	@Column(nullable = false, length = 100)
	private String dsNom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pok", referencedColumnName = "id")
	private PokemonCaptor pokemonCaptor;

	public static synchronized HabilidadeCaptor create() {
		return new HabilidadeCaptor();
	}

	public HabilidadeCaptor withDsNom(final String dsNom) {
		this.dsNom = dsNom;
		return this;
	}

	public HabilidadeCaptor withPokemonCaptor(final PokemonCaptor pokemonCaptor) {
		this.pokemonCaptor = pokemonCaptor;
		return this;
	}

	public String getDsNom() {
		return dsNom;
	}

	public void setDsNom(String dsNom) {
		this.dsNom = dsNom;
	}

	public PokemonCaptor getPokemonCaptor() {
		return pokemonCaptor;
	}

	public void setPokemonCaptor(PokemonCaptor pokemonCaptor) {
		this.pokemonCaptor = pokemonCaptor;
	}

}