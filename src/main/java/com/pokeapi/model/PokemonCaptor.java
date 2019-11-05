package com.pokeapi.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_poca")
public class PokemonCaptor {

	@Id
	private Integer id;

	@Column(nullable = false, length = 100)
	private String dsNom;

	@Column(nullable = false, precision = 3)
	private Integer nrHei;

	@Column(nullable = false, precision = 3)
	private Integer nrWei;

	@Column(nullable = false)
	private LocalDate dtEntSis;

	@OneToMany(mappedBy = "pokemonCaptor",
			   fetch = FetchType.LAZY,
			   orphanRemoval = true,
			   cascade = CascadeType.ALL)
	private List<AbilidadeCaptor> abilidades;

	public static synchronized PokemonCaptor create() {
		return new PokemonCaptor();
	}

	public PokemonCaptor withId(final Integer id) {
		this.id = id;
		return this;
	}

	public PokemonCaptor withDsNom(final String dsNom) {
		this.dsNom = dsNom;
		return this;
	}

	public PokemonCaptor withNrHei(final Integer nrHei) {
		this.nrHei = nrHei;
		return this;
	}

	public PokemonCaptor withNrWei(final Integer nrWei) {
		this.nrWei = nrWei;
		return this;
	}

	public PokemonCaptor withDtEntSis(final LocalDate dtEntSis) {
		this.dtEntSis = dtEntSis;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDsNom() {
		return dsNom;
	}

	public void setDsNom(String dsNom) {
		this.dsNom = dsNom;
	}

	public Integer getNrHei() {
		return nrHei;
	}

	public void setNrHei(Integer nrHei) {
		this.nrHei = nrHei;
	}

	public Integer getNrWei() {
		return nrWei;
	}

	public void setNrWei(Integer nrWei) {
		this.nrWei = nrWei;
	}

	public LocalDate getDtEntSis() {
		return dtEntSis;
	}

	public void setDtEntSis(LocalDate dtEntSis) {
		this.dtEntSis = dtEntSis;
	}

	public List<AbilidadeCaptor> getAbilidades() {
		return abilidades;
	}

	public void setAbilidades(List<AbilidadeCaptor> abilidades) {
		this.abilidades = abilidades;
	}

}