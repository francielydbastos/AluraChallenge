package com.example.controlefinanceiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controlefinanceiro.entity.Categorias;
import com.example.controlefinanceiro.entity.Despesas;
import com.example.controlefinanceiro.repository.DespesasRepository;

@Service
public class DespesasService {

	@Autowired
	DespesasRepository repository;

	public String save(Despesas despesa) {
		
		if (despesa.getCategoria() == null || despesa.getCategoria().equals("")) {
			despesa.setCategoria(Categorias.OUTROS.toString());
		} else if (despesa.getCategoria().equalsIgnoreCase("alimentação") || despesa.getCategoria().equalsIgnoreCase("alimentaçao")
				|| despesa.getCategoria().equalsIgnoreCase("alimentacão") || despesa.getCategoria().equalsIgnoreCase("alimentacao")) {
			despesa.setCategoria(Categorias.ALIMENTAÇÃO.toString());
		} else if (despesa.getCategoria().equalsIgnoreCase("educação") || despesa.getCategoria().equalsIgnoreCase("educaçao")
				|| despesa.getCategoria().equalsIgnoreCase("educacão") || despesa.getCategoria().equalsIgnoreCase("educacao")) {
			despesa.setCategoria(Categorias.EDUCAÇÃO.toString());
		} else if (despesa.getCategoria().equalsIgnoreCase("saúde") || despesa.getCategoria().equalsIgnoreCase("saude")) {
			despesa.setCategoria(Categorias.SAÚDE.toString());
		} else if (despesa.getCategoria().equalsIgnoreCase("moradia")) {
			despesa.setCategoria(Categorias.MORADIA.toString());
		} else if (despesa.getCategoria().equalsIgnoreCase("transporte")) {
			despesa.setCategoria(Categorias.TRANSPORTE.toString());
		} else if (despesa.getCategoria().equalsIgnoreCase("lazer")) {
			despesa.setCategoria(Categorias.LAZER.toString());
		} else if (despesa.getCategoria().equalsIgnoreCase("imprevistos")) {
			despesa.setCategoria(Categorias.IMPREVISTOS.toString());
		} else {
			despesa.setCategoria(Categorias.OUTROS.toString());
		}
		
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

	public List<Despesas> getByDescricao(String descricao) {
		List<Despesas> despesas = getAllDespesas();
		
		List<Despesas> listaDespesas = new ArrayList<Despesas>();
		for (Despesas despesa : despesas) {
			if (despesa.getDescricao().contains(descricao)) {
				listaDespesas.add(despesa);
			}
		}
		
		return listaDespesas;
	}

	public List<Despesas> getByDate(int ano, int mes) {
		List<Despesas> despesas = getAllDespesas();
		
		List<Despesas> listaDespesas = new ArrayList<Despesas>();
		for (Despesas despesa : despesas) {
			if (despesa.getData().getYear() == ano && despesa.getData().getMonthValue() == mes) {
				listaDespesas.add(despesa);
			}
		}
		
		return listaDespesas;
	}
}
