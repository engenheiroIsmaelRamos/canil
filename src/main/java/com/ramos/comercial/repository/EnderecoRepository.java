package com.ramos.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramos.comercial.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	Optional<Endereco> findByCidadeAndEstadoAndPaisAndBairroAndNumeroAndRuaAndCep(String cidade, String estado, String pais, String bairro, int numero, String rua, int cep);

	


}
