package com.ramos.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramos.comercial.model.Padreador;
import com.ramos.comercial.repository.PadreadorRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/padreador")
public class PadreadorController {
	
	@Autowired
	private PadreadorRepository padreadores;
	
	@GetMapping
	public List<Padreador> listar(){
		return padreadores.findAll();
	}
}
