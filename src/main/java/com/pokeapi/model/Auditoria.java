package com.pokeapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_audi")
public class Auditoria extends DefaultModel {

	@Column(nullable = false, length = 3)
	private String method;

	@Column(nullable = false, length = 50)
	private String scheme;

	@Column(nullable = false, length = 50)
	private String host;

	@Column(nullable = false, length = 50)
	private String path;

	private LocalDate dtCon;

	public static synchronized Auditoria create() {
		return new Auditoria();
	}

	public Auditoria withMethod(final String method) {
		this.method = method;
		return this;
	}

	public Auditoria withScheme(final String scheme) {
		this.scheme = scheme;
		return this;
	}

	public Auditoria withHost(final String host) {
		this.host = host;
		return this;
	}

	public Auditoria withPath(final String path) {
		this.path = path;
		return this;
	}

	public Auditoria withDtCon(final LocalDate dtCon) {
		this.dtCon = dtCon;
		return this;
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

	public LocalDate getDtCon() {
		return dtCon;
	}

	public void setDtCon(LocalDate dtCon) {
		this.dtCon = dtCon;
	}

}