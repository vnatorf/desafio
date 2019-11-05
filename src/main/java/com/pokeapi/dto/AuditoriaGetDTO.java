package com.pokeapi.dto;

import java.time.LocalDate;

import org.dozer.Mapping;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "DTO com as informações de auditoria")
public class AuditoriaGetDTO {

	@ApiModelProperty(notes = "Identificador da auditoria")
	@Mapping("id")
	private Integer identificador;

	@ApiModelProperty(notes = "Método executado")
	@Mapping("method")
	private String method;

	@ApiModelProperty(notes = "Scheme do serviço chamado")
	@Mapping("scheme")
	private String scheme;

	@ApiModelProperty(notes = "Host do serviço chamado")
	@Mapping("host")
	private String host;

	@ApiModelProperty(notes = "Path do serviço chamado")
	@Mapping("path")
	private String path;

	@ApiModelProperty(notes = "Data de quando foi executada a chamada do serviço")
	@Mapping("dtCon")
	private LocalDate dataConsulta;

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

}