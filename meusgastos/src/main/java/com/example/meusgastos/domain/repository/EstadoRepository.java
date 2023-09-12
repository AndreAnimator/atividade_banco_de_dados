package com.example.meusgastos.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meusgastos.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
    Optional<Estado> findById(Long id);
}