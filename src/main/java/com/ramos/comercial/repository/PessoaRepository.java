package com.ramos.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramos.comercial.model.Pessoa;
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	Optional<Pessoa> findByCpfCnpjAndNome(String cpf_cnpj, String nome);

	Optional<Pessoa> findByIdAndCodEndereco(Long id, Long cod_endereco);
		
}
