package com.pokeapi.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pokeapi.component.MensagensSource;
import com.pokeapi.exception.PokeApiException;
import com.pokeapi.exception.dto.PokeApiErroHandlerDTO;
import com.pokeapi.util.ChaveMensagemUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class PokeApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	protected MensagensSource mensagensSource;

	/**
	 * Método responsável por manipular as exceções do tipo PokeApiException. O mesmo irá buscar
	 * as mensagens de cada uma à partir da chave recebida. Caso haja algum parâmetro, o mesmo será utilizado para compor a mensagem.
	 *  
	 * @param exception PokeApiException recebida da API
	 * @return Retorna o ResponseEntity com seu código de erro e mensagem
	 */
	@ExceptionHandler(value = { PokeApiException.class })
	protected ResponseEntity<Object> handlePokeApi(final PokeApiException exception) {
		String mensagem;

		if (CollectionUtils.isNotEmpty(exception.getParametros())) {
			mensagem = String.format(this.mensagensSource.getMessagem(exception.getMessage()),
									 exception.getParametros());

		} else {
			mensagem = this.mensagensSource.getMessagem(exception.getMessage());
		}

		return new ResponseEntity(PokeApiErroHandlerDTO
									.create()
									.withStatus(HttpStatus.BAD_REQUEST)
									.withDetalhe(mensagem),
								  HttpStatus.BAD_REQUEST);
	}

	/**
	 * Método responsável por manipular as exceções não esperadas vindas do serviço chamados.
	 * Segue o mesmo padrão chave para busca da mensagem.
	 * 
	 * @param exception HttpClientErrorException recebida da API
	 * @return Retorna o ResponseEntity com seu código de erro e mensagem
	 */
	@ExceptionHandler(value = { HttpClientErrorException.class })
	protected ResponseEntity<Object> handleHttpClientError(final HttpClientErrorException exception) {
		return new ResponseEntity(PokeApiErroHandlerDTO
									.create()
									.withStatus(exception.getStatusCode())
									.withDetalhe(this.mensagensSource.getMessagem(ChaveMensagemUtil.DEFAULT.CLIENT_ERROR)),
								  exception.getStatusCode());
	}

	/**
	 * Método responsável por manipular as exceções não esperadas vindas da própria aplicação.
	 * Segue o mesmo padrão chave para busca da mensagem.
	 * 
	 * @param exception RuntimeException recebida da API
	 * @return Retorna o ResponseEntity com seu código de erro e mensagem
	 */
	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<Object> handleDefault(final RuntimeException exception) {
		return new ResponseEntity(PokeApiErroHandlerDTO
									.create()
									.withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
									.withDetalhe(this.mensagensSource.getMessagem(ChaveMensagemUtil.DEFAULT.NOT_EXPECTED)),
								  HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * Método handleMethodArgumentNotValid sofreu @Override manipular as exceções vindas das anotações dos DTO's.
	 * Segue o mesmo padrão chave para busca da mensagem.
	 * 
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return Retorna o ResponseEntity com seu código de erro e mensagem 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
																  final HttpHeaders headers,
																  final HttpStatus status,
																  final WebRequest request) {

		return new ResponseEntity(PokeApiErroHandlerDTO
									.create()
									.withStatus(HttpStatus.BAD_REQUEST)
									.withDetalhe(this.getDetalhe(exception)),
								  HttpStatus.BAD_REQUEST);
	}

	/**
	 * Método responsável por pegar cada chave de erro e encontrar sua respectiva mensagem.
	 * 
	 * Utilizado no método: {@link #handleMethodArgumentNotValid(MethodArgumentNotValidException, HttpHeaders, HttpStatus, WebRequest)}
	 * 
	 * @param exception MethodArgumentNotValidException recebida da API
	 * @return Retorna a lista de mensagens
	 */
	private List<String> getDetalhe(final MethodArgumentNotValidException exception) {
		return exception
				.getBindingResult()
				.getAllErrors()
				.parallelStream()
				.map(error -> error.getDefaultMessage())
				.map(this.mensagensSource::getMessagem)
				.collect(Collectors.toList());
	}

}