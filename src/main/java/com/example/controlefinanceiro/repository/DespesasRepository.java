package com.example.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controlefinanceiro.entity.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {

}
