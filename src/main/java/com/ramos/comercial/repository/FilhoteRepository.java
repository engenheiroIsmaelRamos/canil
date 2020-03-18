package com.ramos.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramos.comercial.model.Cao;
import com.ramos.comercial.model.Filhote;

public interface FilhoteRepository extends JpaRepository<Filhote, Long>{
	
	Optional<Filhote> findByCao(Cao cao);
}
