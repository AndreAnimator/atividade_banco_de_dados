package com.example.meusgastos.domain.dto.logradouro;

import com.example.meusgastos.domain.model.Cidade;

public class LogradouroRequestDTO {
    private Long id;
    private String nomeRua;
    private Double cep;
    private Cidade cidade;
    
    public Cidade getCidade() {
        return cidade;
    }
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    public String getNomeRua() {
        return nomeRua;
    }
    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }
    public Double getCep() {
        return cep;
    }
    public void setCep(Double cep) {
        this.cep = cep;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
