package com.ramos.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramos.comercial.model.Cao;
import com.ramos.comercial.model.Matriz;

public interface MatrizRepository extends JpaRepository<Matriz, Long> {
	
	Optional<Matriz> findByCao(Cao cao);

}
