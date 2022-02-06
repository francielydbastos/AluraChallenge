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
import com.example.controlefinanceiro.entity.Despesas;
import com.example.controlefinanceiro.service.DespesasService;

@RestController
@RequestMapping (path = "/despesas")
public class DespesasController {
	
	@Autowired
	DespesasService service;

	@PostMapping
	public String create(@RequestBody Despesas despesa) throws Exception {
		
		return service.save(despesa);
	}

	@GetMapping
	private List<Despesas> getAllDespesas() 
	{
		return service.getAllDespesas();
	}

	@GetMapping("/{id}")
	public Despesas getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<Despesas> getById(@PathVariable int ano, @PathVariable int mes) {
		return service.getByDate(ano, mes);
	}
	
	@GetMapping("/{descricao}")
	public List<Despesas> getByDescricao(@PathVariable String descricao) {
		return service.getByDescricao(descricao);
	}

	@PutMapping("/{id}")
	public String update(@RequestBody Despesas despesa, @PathVariable Long id) throws Exception {

		return service.update(id, despesa);
	}

	@DeleteMapping("/{id}")
	private void deleteStudent(@PathVariable Long id) {
		service.delete(id);
	}

}
