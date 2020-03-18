package com.ramos.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ramos.comercial.model.Cao;
import com.ramos.comercial.repository.CaoRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/caes")
public class CaoController {
	
	@Autowired
	private CaoRepository caes;
	
	@GetMapping
	public List<Cao> listar(){
		
		return caes.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cao> buscar(@PathVariable Long id){
		
		Optional<Cao> cao = caes.findById(id);
		
		if(cao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cao.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cao inserir(@Valid @RequestBody Cao cao) {
		
		Optional<Cao> caoExistente = caes.findByNomeAndDoc(cao.getNome(), cao.getDoc());
		
		if(caoExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um cão cadastrado com esse nome e documento!");
		}
		return caes.save(cao);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
	public Cao atualizar(@Valid @RequestBody Cao cao) {
		
		Optional<Cao> caoExistente = caes.findByNomeAndDoc(cao.getNome(), cao.getDoc());
		
		if(caoExistente.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cão não registrado");
		}
		
		return caes.save(cao);
		
	}

}
