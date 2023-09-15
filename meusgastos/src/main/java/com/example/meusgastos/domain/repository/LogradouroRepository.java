package com.example.meusgastos.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.meusgastos.domain.model.Cidade;
import com.example.meusgastos.domain.model.Logradouro;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long>{
    Optional<Logradouro> findById(Long id);
    List<Logradouro> findByCidade(Cidade cidade);
}
    //Genuinamente não tenho ideia de como fazer os repository
