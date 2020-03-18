package com.ramos.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramos.comercial.model.Cao;
import com.ramos.comercial.model.Proprietario;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long>{
	
	Optional<Proprietario> findByCao(Cao cao);
}
