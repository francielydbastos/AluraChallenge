package com.example.controlefinanceiro.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.controlefinanceiro.entity.Receitas;
import com.example.controlefinanceiro.repository.ReceitasRepository;

@Service
public class ReceitasService {

	@Autowired
	ReceitasRepository repository;

	public String save(Receitas receita) {

		List<Receitas> receitas = getAllReceitas();

		for (Receitas receitaSalva : receitas) {
			if (receita.getData().getMonthValue() == receitaSalva.getData().getMonthValue() && receita.getDescricao().equals(receitaSalva.getDescricao())) {
				return "Não é possível salvar receitas duplicadas";
			}
		}

		repository.save(receita);
		return "Receita salva com sucesso";
	}

	public List<Receitas> getAllReceitas() 
	{
		List<Receitas> receitas = new ArrayList<Receitas>();
		repository.findAll().forEach(receita -> receitas.add(receita));
		return receitas;
	}
	
	public Receitas getById(Long id) 
	{
		return repository.findById(id).orElseThrow();
	}
	
	public String update(Long id, Receitas receita) {
		
		List<Receitas> receitas = getAllReceitas();

		for (Receitas receitaNoBanco : receitas) {
			if (receita.getData().getMonthValue() == receitaNoBanco.getData().getMonthValue() 
					&& receita.getDescricao().equals(receitaNoBanco.getDescricao())
					&& receitaNoBanco.getId() != id) {
				return "Não é possível salvar receitas duplicadas";
			}
		}
		
		Receitas receitaSalva = getById(id);
		receitaSalva.setData(receita.getData());
		receitaSalva.setDescricao(receita.getDescricao());
		receitaSalva.setValor(receita.getValor());
		
		repository.save(receitaSalva);
		return "Receita atualizada com sucesso";
	}
	
	public void delete(Long id) 
	{
		try {
			repository.deleteById(id);
		} catch(Exception e){
			e.getMessage();
		}
	}
	
	public List<Receitas> getByDescricao(String descricao) {
		List<Receitas> receitas = getAllReceitas();
		
		List<Receitas> listaReceitas = new ArrayList<Receitas>();
		for (Receitas receita : receitas) {
			if (receita.getDescricao().contains(descricao)) {
				listaReceitas.add(receita);
			}
		}
		
		return listaReceitas;
	}
	
	public List<Receitas> getByDate(int ano, int mes) {
		List<Receitas> receitas = getAllReceitas();
		
		List<Receitas> listaReceitas = new ArrayList<Receitas>();
		for (Receitas receita : receitas) {
			if (receita.getData().getYear() == ano && receita.getData().getMonthValue() == mes) {
				listaReceitas.add(receita);
			}
		}
		
		return listaReceitas;
	}

}
