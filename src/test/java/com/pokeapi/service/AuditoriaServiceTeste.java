package com.pokeapi.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pokeapi.model.Auditoria;
import com.pokeapi.repository.AuditoriaRepository;
import com.querydsl.core.types.Predicate;

@RunWith(SpringRunner.class)
public class AuditoriaServiceTeste {

	@InjectMocks
	private AuditoriaService service;

	@Mock
	private AuditoriaRepository repository;

	@Test
	public void incluir_whenPokemonsEmpty() {
		final HttpRequest request = Mockito.mock(HttpRequest.class);
		final URI uri = Mockito.mock(URI.class);

		when(request.getURI()).thenReturn(uri);

		this.service.incluir(request);

		verify(this.repository).saveAndFlush(Mockito.any(Auditoria.class));
	}

	@Test
	public void consultar_test() {
		final Integer id = 1;
		this.service.consultar(id);
		verify(this.repository).findById(id);
	}

	@Test
	public void listar_withoutParameters() throws Exception {
		this.service.listar(null, null);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

	@Test
	public void listar_withPath() throws Exception {
		final String path = "teste1";

		this.service.listar(path, null);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

	@Test
	public void listar_withDataConsulta() throws Exception {
		final LocalDate dataConsulta = LocalDate.now();

		this.service.listar(null, dataConsulta);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

	@Test
	public void listar_withAllParameters() throws Exception {
		final String path = "teste1";
		final LocalDate dataConsulta = LocalDate.now();

		this.service.listar(path, dataConsulta);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

}