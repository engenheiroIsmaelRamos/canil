package com.ramos.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramos.comercial.model.Matriz;
import com.ramos.comercial.repository.MatrizRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/matriz")
public class MatrizController {
	
	@Autowired
	private MatrizRepository matrizes;
	
	@GetMapping
	public List<Matriz> listar(){
		return matrizes.findAll();
	}
}
