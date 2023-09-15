package com.example.meusgastos.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meusgastos.domain.model.Cidade;
import com.example.meusgastos.domain.model.Estado;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    Optional<Cidade> findById(Long id);
    List<Cidade> findByEstado(Estado estado);//não sei se é um list... você vai ter q ver isso sinto muito
}