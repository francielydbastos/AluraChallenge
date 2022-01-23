package com.example.controlefinanceiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controlefinanceiro.entity.Despesas;
import com.example.controlefinanceiro.repository.DespesasRepository;

@Service
public class DespesasService {

	@Autowired
	DespesasRepository repository;

	public String save(Despesas despesa) {

		List<Despesas> despesas = getAllDespesas();

		for (Despesas despesaSalva : despesas) {
			if (despesa.getData().getMonthValue() == despesaSalva.getData().getMonthValue() && despesa.getDescricao().equals(despesaSalva.getDescricao())) {
				return "Não é possível salvar despesas duplicadas";
			}
		}

		repository.save(despesa);
		return "Despesa salva com sucesso";
	}

	public List<Despesas> getAllDespesas() 
	{
		List<Despesas> despesas = new ArrayList<Despesas>();
		repository.findAll().forEach(despesa -> despesas.add(despesa));
		return despesas;
	}
	
	public Despesas getById(Long id) 
	{
		return repository.findById(id).orElseThrow();
	}
	
	public String update(Long id, Despesas despesa) {
		
		List<Despesas> despesas = getAllDespesas();

		for (Despesas despesaNoBanco : despesas) {
			if (despesa.getData().getMonthValue() == despesaNoBanco.getData().getMonthValue() 
					&& despesa.getDescricao().equals(despesaNoBanco.getDescricao())
					&& despesaNoBanco.getId() != id) {
				return "Não é possível salvar despesas duplicadas";
			}
		}
		
		Despesas despesaSalva = getById(id);
		despesaSalva.setData(despesa.getData());
		despesaSalva.setDescricao(despesa.getDescricao());
		despesaSalva.setValor(despesa.getValor());
		
		repository.save(despesaSalva);
		return "Despesa atualizada com sucesso";
	}
	
	public void delete(Long id) 
	{
		try {
			repository.deleteById(id);
		} catch(Exception e){
			e.getMessage();
		}
	}
}
