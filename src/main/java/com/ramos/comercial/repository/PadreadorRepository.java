package com.ramos.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramos.comercial.model.Cao;
import com.ramos.comercial.model.Padreador;

public interface PadreadorRepository extends JpaRepository<Padreador, Long>{
	
	Optional<Padreador> findByCao(Cao cao);

}
