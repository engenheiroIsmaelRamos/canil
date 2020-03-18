package com.ramos.comercial.controller;

import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ramos.comercial.model.Pessoa;
import com.ramos.comercial.repository.EnderecoRepository;
import com.ramos.comercial.repository.PessoaRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoas;
	
	@Autowired
	private EnderecoRepository enderecos;

	@GetMapping
	public List<Pessoa> listar(){
		return pessoas.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoas.findById(id);
		
		if(pessoa.isEmpty()) {
			System.out.println("Pessoa não encontrada!");
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa.get());
		
	}
	
	@PostMapping("/{cod_endereco}")
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa inserir(@PathVariable (value = "cod_endereco") Long cod_endereco, @Valid @RequestBody Pessoa pessoa) {
	
		return enderecos.findById(cod_endereco).map(endereco -> {
			pessoa.setEndereco(endereco);
			return pessoas.save(pessoa);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "endereco não encontrado"));
		
	}
	
	@PutMapping("/{id}/endereco/{cod_endereco}")
	@ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
	public Pessoa atualizar(@PathVariable (value = "id") Long id, @PathVariable (value = "cod_endereco") Long cod_endereco, @Valid @RequestBody Pessoa pessoa) {
		
		Optional<Pessoa> buscaPessoa = pessoas.findById(id);
		if(buscaPessoa.isEmpty()) {
			System.out.println("Pessoa não encontrada!");
			return null;
		}else {
			return enderecos.findById(cod_endereco).map(endereco -> {
				pessoa.setEndereco(endereco);
				pessoa.setNome(pessoa.getNome());
				pessoa.setEmail(pessoa.getEmail());
				pessoa.setCpfCnpj(pessoa.getCpfCnpj());
				return pessoas.save(pessoa);
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "endereco não encontrado"));
			
		}
	}
	
	@DeleteMapping("/{id}/endereco/{cod_endereco}")
	public ResponseEntity <?> delete (@PathVariable (value = "id") Long id, @PathVariable (value="cod_endereco") Long cod_endereco) throws ResponseStatusException{
		return pessoas.findByIdAndCodEndereco(id, cod_endereco).map(pessoa ->{
			pessoas.delete(pessoa);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada com id" + id + "e código de endereco" + cod_endereco));
	}
	
}
