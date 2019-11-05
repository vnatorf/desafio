package com.pokeapi.service.client.dto;

import java.util.List;

public class ListaDTO {

	private Integer count;

	private List<ListaResultDTO> results;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ListaResultDTO> getResults() {
		return results;
	}

	public void setResults(List<ListaResultDTO> results) {
		this.results = results;
	}

}