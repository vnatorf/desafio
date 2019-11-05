package com.pokeapi.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.pokeapi.component.AuditoriaInterceptor;
import com.pokeapi.util.ChaveApplicationPropertiesUtil;

@Configuration
public class RestConfig {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Método responsável por montar o objeto RestOperations com o interceptor de auditoria.
	 * 
	 * @param clientHttpRequestFactory
	 * @return Retorna o objeto restOperations
	 */
	@Bean
	public RestOperations createRestTemplate(final ClientHttpRequestFactory clientHttpRequestFactory) {
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}

		interceptors.add(this.initAuditoriaInterceptor());
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}

	/**
	 * Método responsável por montar o HttpComponentsClientHttpRequestFactory com o connectTimeout e readTimeout
	 * configurados no arquivo 'application.properties'.
	 * 
	 * @param connectTimeout ConnectTimeout configurado no arquivo 'application.properties'
	 * @param readTimeout ReadTimeout configurado no arquivo 'application.properties'
	 * @return
	 */
	@Bean
	public ClientHttpRequestFactory create(@Value(ChaveApplicationPropertiesUtil.SERVICE.CONNECT_TIMEOUT)
										   final Integer connectTimeout,
										   @Value(ChaveApplicationPropertiesUtil.SERVICE.READ_TIMEOUT)
										   final Integer readTimeout) {

		final HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(connectTimeout);
		httpRequestFactory.setReadTimeout(readTimeout);
		return httpRequestFactory;
	}

	/**
	 * Método responsável por inicializar e registrar o interceptor de auditoria.
	 * 
	 * @return Retorna o objeto auditoriaInterceptor
	 */
	private AuditoriaInterceptor initAuditoriaInterceptor() {
		final AuditoriaInterceptor interceptor = new AuditoriaInterceptor();
		final AutowireCapableBeanFactory factory = this.applicationContext.getAutowireCapableBeanFactory();
		factory.autowireBean(interceptor);
		factory.initializeBean(interceptor, AuditoriaInterceptor.class.getSimpleName());
		return interceptor;
	}

}