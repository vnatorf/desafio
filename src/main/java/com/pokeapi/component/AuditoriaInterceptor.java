package com.pokeapi.component;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.pokeapi.service.AuditoriaService;

@Component
public class AuditoriaInterceptor implements ClientHttpRequestInterceptor {

	@Autowired
	private AuditoriaService auditoriaService;

	/**
	 * Método intercept sofreu @Override para inclusão da auditoria a cada chamada de serviço.
	 * 
	 * @param request the request, containing method, URI, and headers
	 * @param body the body of the request
	 * @param execution the request execution
	 * @return the response
	 */
	@Override
	public ClientHttpResponse intercept(final HttpRequest request,
										final byte[] body,
										final ClientHttpRequestExecution execution) throws IOException {

		this.auditoriaService.incluir(request);
		return execution.execute(request, body);
	}

}