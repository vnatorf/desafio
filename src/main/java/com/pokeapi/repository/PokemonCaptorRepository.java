package com.pokeapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.pokeapi.model.PokemonCaptor;

@Repository
public interface PokemonCaptorRepository extends JpaRepository<PokemonCaptor, Integer>, QuerydslPredicateExecutor<PokemonCaptor> {

	public Optional<PokemonCaptor> findByDsNom(String dsNom);

}