package com.pokeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.pokeapi.model.Auditoria;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer>, QuerydslPredicateExecutor<Auditoria> {
}