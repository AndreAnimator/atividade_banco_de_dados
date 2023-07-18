package com.example.meusgastos.domain.dto.centrodecusto;

public class CentroDeCustoRequestDTO {
    private Long id;
    private String decricao;
    private String observacao;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDecricao() {
        return decricao;
    }
    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
}
