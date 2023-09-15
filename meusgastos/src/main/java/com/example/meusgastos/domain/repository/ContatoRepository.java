package com.example.meusgastos.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meusgastos.domain.model.Contato;
import com.example.meusgastos.domain.model.Usuario;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
    Optional<Contato> findById(Long id);//eu não sei como faz repository mas eu acredito que seja isso?
    List<Contato> findByUsuario(Usuario usuario);//não sei se é um list... você vai ter q ver isso sinto muito
}