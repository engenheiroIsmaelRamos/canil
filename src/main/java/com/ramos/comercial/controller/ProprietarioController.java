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

import com.ramos.comercial.model.Proprietario;
import com.ramos.comercial.repository.ProprietarioRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {
	
	@Autowired
	private ProprietarioRepository proprietarios;
	
	@GetMapping
	public List<Proprietario> listar(){
		return proprietarios.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Proprietario inserir(@Valid @RequestBody Proprietario dono) {
		
		Optional<Proprietario> proprietarioExiste = proprietarios.findByCao(dono.getCao());
		
		if(proprietarioExiste.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este cão já possui dono!");
		}
		
		return proprietarios.save(dono);
	}
}
