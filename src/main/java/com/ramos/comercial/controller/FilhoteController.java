package com.ramos.comercial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramos.comercial.model.Filhote;
import com.ramos.comercial.repository.FilhoteRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/filhote")
public class FilhoteController {
	
	@Autowired
	private FilhoteRepository filhotes;
	
	@GetMapping
	public List<Filhote> listar(){
		return filhotes.findAll();
	}

}
