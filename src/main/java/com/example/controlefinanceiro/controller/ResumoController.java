package com.example.controlefinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlefinanceiro.service.ResumoService;

@RestController
@RequestMapping (path = "/resumo")
public class ResumoController {

	@Autowired
	ResumoService service;
	
	@GetMapping("/{ano}/{mes}")
	public String getResumo(@PathVariable int ano, @PathVariable int mes) {
		return service.getByDate(ano, mes);
	}
}
