package com.pokeapi.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.pokeapi.dto.PokemonCaptorGetDTO;
import com.pokeapi.dto.PokemonCaptorPostDTO;
import com.pokeapi.dto.builder.HabilidadeDTOBuilderTeste;
import com.pokeapi.dto.builder.ListaHabilidadeDTOBuilderTeste;
import com.pokeapi.dto.builder.PokemonDTOBuilderTeste;
import com.pokeapi.dto.builder.PokemonTheftBuilderTeste;
import com.pokeapi.dto.builder.PokemonTheftGetDTOBuilderTeste;
import com.pokeapi.dto.builder.PokemonTheftPostDTOBuilderTeste;
import com.pokeapi.model.PokemonCaptor;
import com.pokeapi.repository.PokemonCaptorRepository;
import com.pokeapi.service.client.dto.HabilidadeDTO;
import com.pokeapi.service.client.dto.ListaHabilidadeDTO;
import com.pokeapi.service.client.dto.PokemonDTO;
import com.pokeapi.util.ChaveMensagemUtil;
import com.pokeapi.util.PokeApiTestUtil;
import com.querydsl.core.types.Predicate;

@RunWith(SpringRunner.class)
public class PokemonCaptorServiceTeste extends DefaultServiceTeste {

	@InjectMocks
	private PokemonCaptorService service;

	@Mock
	private PokemonCaptorRepository repository;

	@Mock
	private PokemonService pokemonService;

	@Test
	public void capturar_whenPokemonsEmpty() {
		final PokemonCaptorPostDTO dto = PokemonTheftPostDTOBuilderTeste.mock();
		final Set<String> pokemons = PokeApiTestUtil.getSetString(0, null);

		when(dto.getPokemons()).thenReturn(pokemons);

		super.buildThrownExpect(ChaveMensagemUtil.THEFT.PARAM_POKEMONS_NOT_EMPTY);

		this.service.capturar(dto);
	}

	@Test
	public void capturar_whenPokemonsBiggerThen3() {
		final PokemonCaptorPostDTO dto = PokemonTheftPostDTOBuilderTeste.mock();
		final Set<String> pokemons = PokeApiTestUtil.getSetString(4, null);

		when(dto.getPokemons()).thenReturn(pokemons);

		super.buildThrownExpect(ChaveMensagemUtil.THEFT.PARAM_POKEMONS_MAX);

		this.service.capturar(dto);
	}

	@Test
	public void capturar_whenPokemonsAlreadyStolenById() {
		final PokemonCaptorPostDTO dto = PokemonTheftPostDTOBuilderTeste.mock();
		final Set<String> pokemons = PokeApiTestUtil.getSetString(1, null);
		final Optional<PokemonCaptor> optionalPokemonTheft = PokemonTheftBuilderTeste.optional();

		when(dto.getPokemons()).thenReturn(pokemons);
		when(this.repository.findById(1)).thenReturn(optionalPokemonTheft);

		super.buildThrownExpect(ChaveMensagemUtil.THEFT.POKEMON_ALREADY_CAPTURED);

		this.service.capturar(dto);
	}

	@Test
	public void capturar_whenPokemonsAlreadyStolenByName() {
		final PokemonCaptorPostDTO dto = PokemonTheftPostDTOBuilderTeste.mock();
		final Set<String> pokemons = PokeApiTestUtil.getSetString(1, "teste");
		final Optional<PokemonCaptor> optionalPokemonTheft = PokemonTheftBuilderTeste.optional();

		when(dto.getPokemons()).thenReturn(pokemons);
		when(this.repository.findByDsNom("1teste")).thenReturn(optionalPokemonTheft);

		super.buildThrownExpect(ChaveMensagemUtil.THEFT.POKEMON_ALREADY_CAPTURED);

		this.service.capturar(dto);
	}

	@Test
	public void capturar_whenSucessWithoutAbilities() {
		final PokemonCaptorPostDTO dto = PokemonTheftPostDTOBuilderTeste.mock();
		final Set<String> pokemons = PokeApiTestUtil.getSetString(1, null);
		final Optional<PokemonCaptor> optionalEmpty = Optional.empty();
		final PokemonDTO pokemonDTO = PokemonDTOBuilderTeste.mock();
		final PokemonCaptor entity = PokemonTheftBuilderTeste.mock();
		final PokemonCaptorGetDTO theftGetDTO = PokemonTheftGetDTOBuilderTeste.mock();

		when(dto.getPokemons()).thenReturn(pokemons);
		when(this.repository.findById(1)).thenReturn(optionalEmpty);
		when(this.pokemonService.get("1")).thenReturn(pokemonDTO);
		when(this.repository.save(entity)).thenReturn(entity);
		when(super.mapper.map(entity, PokemonCaptorGetDTO.class)).thenReturn(theftGetDTO);

		this.service.capturar(dto);
	}

	@Test
	public void capturar_whenSucessWithAbilities() {
		final PokemonCaptorPostDTO dto = PokemonTheftPostDTOBuilderTeste.mock();
		final Set<String> pokemons = PokeApiTestUtil.getSetString(1, null);
		final Optional<PokemonCaptor> optionalEmpty = Optional.empty();
		final PokemonDTO pokemonDTO = PokemonDTOBuilderTeste.mock();
		final ListaHabilidadeDTO listaAbilidadeDTO = ListaHabilidadeDTOBuilderTeste.mock();
		final HabilidadeDTO habilidadeDTO = HabilidadeDTOBuilderTeste.mock();
		final PokemonCaptor entity = PokemonTheftBuilderTeste.mock();
		final PokemonCaptorGetDTO theftGetDTO = PokemonTheftGetDTOBuilderTeste.mock();

		when(dto.getPokemons()).thenReturn(pokemons);
		when(this.repository.findById(1)).thenReturn(optionalEmpty);
		when(this.pokemonService.get("1")).thenReturn(pokemonDTO);
		when(pokemonDTO.getAbilities()).thenReturn(Arrays.asList(listaAbilidadeDTO));
		when(listaAbilidadeDTO.getAbility()).thenReturn(habilidadeDTO);
		when(this.repository.save(entity)).thenReturn(entity);
		when(super.mapper.map(entity, PokemonCaptorGetDTO.class)).thenReturn(theftGetDTO);

		this.service.capturar(dto);
	}

	@Test
	public void excluir_whenNotFound() {
		final Integer id = 1;
		final Optional<PokemonCaptor> optionalEntity = Optional.empty();

		when(this.repository.findById(id)).thenReturn(optionalEntity);

		super.buildThrownExpect(ChaveMensagemUtil.THEFT.POKEMON_NOT_EXIST);

		this.service.excluir(id);
	}

	@Test
	public void excluir_whenSucess() {
		final Integer id = 1;
		final Optional<PokemonCaptor> optionalEntity = PokemonTheftBuilderTeste.optional();

		when(this.repository.findById(id)).thenReturn(optionalEntity);

		this.service.excluir(id);

		verify(this.repository).delete(optionalEntity.get());
	}

	@Test
	public void consultar_test() {
		final Integer id = 1;
		this.service.consultar(id);
		verify(this.repository).findById(id);
	}

	@Test
	public void listar_withoutParameters() throws Exception {
		this.service.listar(null, null, null);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

	@Test
	public void listar_withNomePokemon() throws Exception {
		final String nomePokemon = "teste1";

		this.service.listar(nomePokemon, null, null);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

	@Test
	public void listar_withIdentificador() throws Exception {
		final Integer identificador = 1;

		this.service.listar(null, identificador, null);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

	@Test
	public void listar_withDataEntradaSistema() throws Exception {
		final LocalDate dataEntradaSistema = LocalDate.now();

		this.service.listar(null, null, dataEntradaSistema);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

	@Test
	public void listar_withAllParameters() throws Exception {
		final String nomePokemon = "teste1";
		final Integer identificador = 1;
		final LocalDate dataEntradaSistema = LocalDate.now();

		this.service.listar(nomePokemon, identificador, dataEntradaSistema);

		verify(this.repository).findAll(Mockito.any(Predicate.class));
	}

}