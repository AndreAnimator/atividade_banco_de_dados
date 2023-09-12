package com.example.meusgastos.domain.dto.cidade;

import com.example.meusgastos.domain.model.Estado;

public class CidadeResponseDTO {
    private Long id;
    private String nomeCidade;
    private Estado estado;
    
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public String getNomeCidade() {
        return nomeCidade;
    }
    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
}

