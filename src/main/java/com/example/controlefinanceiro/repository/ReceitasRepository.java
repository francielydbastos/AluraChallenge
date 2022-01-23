package com.example.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controlefinanceiro.entity.Receitas;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long>{

}
