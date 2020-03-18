package com.ramos.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramos.comercial.model.Cao;

public interface CaoRepository extends JpaRepository<Cao, Long> {
	Optional<Cao>  findByNomeAndDoc(String nome, String doc);
}
