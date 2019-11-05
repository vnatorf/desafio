package com.pokeapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokeapi.dto.PokemonCaptorDetalheGetDTO;
import com.pokeapi.dto.PokemonCaptorGetDTO;
import com.pokeapi.dto.PokemonCaptorPostDTO;
import com.pokeapi.model.HabilidadeCaptor;
import com.pokeapi.model.PokemonCaptor;
import com.pokeapi.model.QPokemonCaptor;
import com.pokeapi.repository.PokemonCaptorRepository;
import com.pokeapi.service.client.dto.PokemonDTO;
import com.pokeapi.util.ChaveMensagemUtil;
import com.pokeapi.util.PokeApiUtil;
import com.querydsl.core.BooleanBuilder;

@Service
public class PokemonCaptorService extends DefaultService {

	@Autowired
	private PokemonCaptorRepository repository;

	@Autowired
	private PokemonService pokemonService;

	/**
	 * Método responsável por capturar os pokemons selecionados. Os mesmos serão consultados na api
	 * <a href="https://pokeapi.co/">PokéAPI</a>. Os pokemons selecionados, caso não existam na base, serão gravados assim
	 * como suas habilidades.
	 * 
	 * Informações serão persistidas nas tabelas:
	 * 		PokemonCaptor: 't_poca'
	 * 		HabilidadeCaptor: 't_abca'
	 * 
	 * @param dto DTO com a lista dos pokemons que serão capturados
	 * @return Retorna os pokemons capturados
	 */
	public List<PokemonCaptorGetDTO> capturar(final PokemonCaptorPostDTO dto) {
		super.assertTrue(CollectionUtils.isNotEmpty(dto.getPokemons()), ChaveMensagemUtil.THEFT.PARAM_POKEMONS_NOT_EMPTY);

		super.assertFalse(dto
							.getPokemons()
							.stream()
							.anyMatch(pokemon -> StringUtils.isEmpty(pokemon)),
						  ChaveMensagemUtil.THEFT.PARAM_POKEMONS_NOT_EMPTY);

		super.assertTrue(dto.getPokemons().size() <= 3, ChaveMensagemUtil.THEFT.PARAM_POKEMONS_MAX);

		dto
		.getPokemons()
		.parallelStream()
		.forEach(this.validaJaCapturado());

		final List<PokemonDTO> pokemons = this.getPokemons(dto);

		return pokemons
				.stream()
				.map(this.mapPokemonDTOToPokemonTheft())
				.peek(this.repository::save)
				.map(pokemonTheft -> super.map(pokemonTheft, PokemonCaptorGetDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * Método responsável por fazer a exclusão do pokemon capturado.
	 * 
	 * @param id Identificador do pokemon na tabela 't_poca'
	 */
	public void excluir(final Integer id) {
		final Optional<PokemonCaptor> optionalEntity = this.repository.findById(id);
		super.assertTrue(optionalEntity.isPresent(), ChaveMensagemUtil.THEFT.POKEMON_NOT_EXIST, id);
		this.repository.delete(optionalEntity.get());
	}

	/**
	 * 
	 * Método responsável por consultar o pokemon capturado.
	 * 
	 * @param id Identificador do pokemon na tabela 't_poca'
	 * @return Retorno o pokemon capturado com suas habilidades
	 */
	public PokemonCaptorDetalheGetDTO consultar(final Integer id) {
		return super.map(this.repository.findById(id), PokemonCaptorDetalheGetDTO.class);
	}

	/**
	 * Método responsável por listar os pokemons capturados à partir do filtro informado.
	 * 
	 * @param nomePokemon Nome do pokemon
	 * @param identificador Identificador do pokemon na tabela 't_poca'
	 * @param dataEntradaSistema Data que o pokemon foi capturado
	 * @return Retorna uma lista com os pokemons
	 */
	public List<PokemonCaptorGetDTO> listar(final String nomePokemon,
										   final Integer identificador,
										   final LocalDate dataEntradaSistema) {

		final BooleanBuilder predicate = this.buildPredicate(nomePokemon, identificador, dataEntradaSistema);

		return super.map(StreamSupport
							.stream(this.repository.findAll(predicate).spliterator(), true)
							.collect(Collectors.toList()),
						 PokemonCaptorGetDTO.class);
	}

	/**
	 * Método responsável por validar se o pokemon já foi capturado. O mesmo utilizado o identificador da tabela 
	 * 't_poca' ou o nome do pokemon.
	 * 
	 * @return
	 */
	private Consumer<? super String> validaJaCapturado() {
		return parametro -> {
			Optional<PokemonCaptor> entityExistente = null;

			final Integer identificador = PokeApiUtil.valueOf(parametro);

			if (identificador != null) {
				entityExistente = this.repository.findById(identificador);
			} else {
				entityExistente = this.repository.findByDsNom(parametro);
			}

			super.assertFalse(entityExistente.isPresent(), ChaveMensagemUtil.THEFT.POKEMON_ALREADY_CAPTURED, parametro);
		};
	}

	/**
	 * Método responsável por consultar na API <a href="https://pokeapi.co/">PokéAPI</a> os pokemons selecionados
	 * para captura. Caso exista duplicidade de pokemon na lista, a segunda consulta será descartada.
	 * 
	 * Utilizado no método: {@link #capturar(PokemonCaptorPostDTO)}
	 * 
	 * @param dto DTO com a lista dos pokemons que serão capturados
	 * @return Retorna a lista com os pokemons para serem capturados 
	 */
	private List<PokemonDTO> getPokemons(final PokemonCaptorPostDTO dto) {
		final List<PokemonDTO> pokemons = new ArrayList<>();

		dto
		.getPokemons()
		.stream()
		.forEach(paramPokemon -> {
			boolean adiciona = true;

			if (CollectionUtils.isNotEmpty(pokemons)) {
				adiciona = !pokemons
							.stream()
							.anyMatch(filtro -> filtro.getId().toString().equals(paramPokemon)
												|| filtro.getName().equalsIgnoreCase(paramPokemon));
			}

			if (adiciona) {
				pokemons.add(this.pokemonService.get(paramPokemon));
			}
		});

		return pokemons;
	}

	/**
	 * Método responsável por transformar o objeto PokemonDTO para o objeto PokemonCaptor.
	 * 
	 * Utilizado no método: {@link #capturar(PokemonCaptorPostDTO)}
	 * 
	 * @return Retorna o pokemon no formato da entidade PokemonCaptor
	 */
	private Function<? super PokemonDTO, ? extends PokemonCaptor> mapPokemonDTOToPokemonTheft() {
		return pokemon -> {
			final PokemonCaptor entity = PokemonCaptor
										.create()
										.withId(pokemon.getId())
										.withDsNom(pokemon.getName())
										.withNrHei(pokemon.getHeight())
										.withNrWei(pokemon.getWeight())
										.withDtEntSis(LocalDate.now());

			entity.setHabilidades(PokeApiUtil
									.emptyIfNull(pokemon.getAbilities())
									.stream()
									.map(abildiade -> HabilidadeCaptor
														.create()
														.withPokemonCaptor(entity)
														.withDsNom(abildiade.getAbility().getName()))
									.collect(Collectors.toList()));

			return entity;
		};
	}

	/**
	 * Método responsável por montar o filtro de pesquisa.
	 * 
	 * Utilizado no método: {@link #listar(String, Integer, LocalDate)}
	 * 
	 * @param nomePokemon Nome do pokemon
	 * @param identificador Identificador do pokemon na tabela 't_poca'
	 * @param dataEntradaSistema Data que o pokemon foi capturado
	 * @return Retorna predicate com os filtros a serem utilizados
	 */
	private BooleanBuilder buildPredicate(final String nomePokemon,
										  final Integer identificador,
										  final LocalDate dataEntradaSistema) {

		final QPokemonCaptor qPokemonCaptor = QPokemonCaptor.pokemonCaptor;
		final BooleanBuilder predicate = new BooleanBuilder();

		Optional
		.ofNullable(nomePokemon)
		.ifPresent(nome -> predicate.and(qPokemonCaptor.dsNom.like(new StringBuilder()
																		.append("%")
																		.append(nome)
																		.append("%").toString())));

		Optional
		.ofNullable(identificador)
		.ifPresent(id -> predicate.and(qPokemonCaptor.id.eq(id)));

		Optional
		.ofNullable(dataEntradaSistema)
		.ifPresent(data -> predicate.and(qPokemonCaptor.dtEntSis.eq(data)));

		return predicate;
	}

}