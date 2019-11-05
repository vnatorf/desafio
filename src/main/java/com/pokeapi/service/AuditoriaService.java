package com.pokeapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.pokeapi.dto.AuditoriaGetDTO;
import com.pokeapi.model.Auditoria;
import com.pokeapi.model.QAuditoria;
import com.pokeapi.repository.AuditoriaRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class AuditoriaService extends DefaultService {

	@Autowired
	private AuditoriaRepository repository;

	/**
	 * Método responsável por incluir a auditoria.
	 * 
	 * Informações serão persistidas nas tabelas:
	 * 		Auditoria: 't_audi'
	 * 
	 * @param request Objeto com as informações necessárias para inclusão da auditoria
	 */
	public void incluir(final HttpRequest request) {
		this.repository.saveAndFlush(Auditoria
										.create()
										.withDtCon(LocalDate.now())
										.withMethod(request.getMethodValue())
										.withScheme(request.getURI().getScheme())
										.withHost(request.getURI().getHost())
										.withPath(request.getURI().getPath()));
	}

	/**
	 * 
	 * Método responsável por consultar a auditoria.
	 * 
	 * @param id Identificador da auditoria na tabela 't_audi'
	 * @return Retorna a auditoria
	 */
	public AuditoriaGetDTO consultar(final Integer id) {
		return super.map(this.repository.findById(id), AuditoriaGetDTO.class);
	}

	/**
	 * Método responsável por listar as auditorias à partir do filtro informado.
	 * 
	 * @param path Path que foi executado
	 * @param dataConsulta Data que foi efetuada a chamada do serviço
	 * @return Retorna uma lista com as auditorias
	 */
	public List<AuditoriaGetDTO> listar(final String path, final LocalDate dataConsulta) {
		final QAuditoria qAuditoria = QAuditoria.auditoria;
		final BooleanBuilder predicate = new BooleanBuilder();

		Optional
		.ofNullable(path)
		.ifPresent(valor -> predicate.and(qAuditoria.path.like(new StringBuilder()
																	.append("%")
																	.append(valor)
																	.append("%").toString())));

		Optional
		.ofNullable(dataConsulta)
		.ifPresent(data -> predicate.and(qAuditoria.dtCon.eq(dataConsulta)));

		return super.map(StreamSupport
							.stream(this.repository.findAll(predicate).spliterator(), true)
							.collect(Collectors.toList()),
						 AuditoriaGetDTO.class);
	}

}