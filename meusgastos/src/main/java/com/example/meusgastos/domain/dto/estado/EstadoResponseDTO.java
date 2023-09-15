package com.example.meusgastos.domain.dto.estado;

public class EstadoResponseDTO {
    private Long id;
    private String nomeEstado;
    
    public String getNomeEstado() {
        return nomeEstado;
    }
    public void setNome(String nomeEstado) {
        this.nomeEstado = nomeEstado; //talvez eu tenha feito uma confusão com os nomes
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}