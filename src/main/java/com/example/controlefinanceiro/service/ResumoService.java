package com.example.controlefinanceiro.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.controlefinanceiro.entity.Despesas;
import com.example.controlefinanceiro.entity.Receitas;

@Service
public class ResumoService {

	@Autowired
	ReceitasService receitasService;
	@Autowired
	DespesasService despesasService;
	
	public String getByDate(int ano, int mes) {
		String resumo = "";
		double totalDespesas = 0;
		double totalReceitas = 0;
		double saldoFinal = 0;
		HashMap<String, Double> gastoCategoria = new HashMap<String, Double>();
		
		List<Despesas> listaDespesas = despesasService.getByDate(ano, mes);
		List<Receitas> listaReceitas = receitasService.getByDate(ano, mes);
		
		for (Despesas despesa : listaDespesas) {
			totalDespesas = totalDespesas + despesa.getValor();
			
			if (Collections.frequency(gastoCategoria.keySet(), despesa.getCategoria()) == 0) {
				gastoCategoria.put(despesa.getCategoria(), despesa.getValor());
			} else if (Collections.frequency(gastoCategoria.keySet(), despesa.getCategoria()) == 1) {
				double tempValor = gastoCategoria.get(despesa.getCategoria());
				gastoCategoria.put(despesa.getCategoria(), (tempValor + despesa.getValor()));
			}
			
		}
		
		for (Receitas receita : listaReceitas) {
			totalReceitas = totalReceitas + receita.getValor();
		}
		
		saldoFinal = totalReceitas - totalDespesas;
		
		resumo = "Total de Despesas: R$" + totalDespesas + ", Total de Receita: R$" + totalReceitas + 
				", Saldo Final: R$" + saldoFinal + ", Gasto por Categoria: " + gastoCategoria.toString();
		
		return resumo;
	}
}
