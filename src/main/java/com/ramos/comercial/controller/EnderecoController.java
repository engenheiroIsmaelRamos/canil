package com.ramos.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ramos.comercial.model.Endereco;
import com.ramos.comercial.repository.EnderecoRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecos;
	
	@GetMapping
	public List<Endereco> listar(){
		return enderecos.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco adicionar(@Valid @RequestBody Endereco endereco) {
		
		Optional<Endereco> enderecoExistente = enderecos.findByCidadeAndEstadoAndPaisAndBairroAndNumeroAndRuaAndCep(endereco.getCidade(), endereco.getEstado(), endereco.getPais(), endereco.getBairro(), endereco.getNumero(), endereco.getRua(), endereco.getCep());
		
		if (enderecoExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um endereco cadastrado com essas informações");
		}
		
		return enderecos.save(endereco);
	}
}
