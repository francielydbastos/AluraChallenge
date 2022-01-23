package com.example.controlefinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlefinanceiro.entity.Receitas;
import com.example.controlefinanceiro.service.ReceitasService;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

	@Autowired
	ReceitasService service;

	@PostMapping
	public String create(@RequestBody Receitas receita) throws Exception {

		return service.save(receita);
	}

	@GetMapping
	private List<Receitas> getAllReceitas() 
	{
		return service.getAllReceitas();
	}

	@GetMapping("/{id}")
	public Receitas getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	public String update(@RequestBody Receitas receita, @PathVariable Long id) throws Exception {

		return service.update(id, receita);
	}

	@DeleteMapping("/{id}")
	private void deleteStudent(@PathVariable Long id) {
		service.delete(id);
	}
}
