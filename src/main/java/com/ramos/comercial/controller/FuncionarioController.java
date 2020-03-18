package com.ramos.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramos.comercial.model.Funcionario;
import com.ramos.comercial.repository.FuncionarioRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping
public class FuncionarioController {
		
	@Autowired
	private FuncionarioRepository funcionarios;
	
	@GetMapping
	public List<Funcionario> listar(){
		return funcionarios.findAll();
	}
}
