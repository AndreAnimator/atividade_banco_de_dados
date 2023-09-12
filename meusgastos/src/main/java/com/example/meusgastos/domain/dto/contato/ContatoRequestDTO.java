package com.example.meusgastos.domain.dto.contato;

import com.example.meusgastos.domain.model.Logradouro;
import com.example.meusgastos.domain.model.Usuario;

public class ContatoRequestDTO {
    private Long id;
    private String nome;
    private Double numero;
    private Double telefone;
    private Double cpf;
    private Usuario usuario;
    private Logradouro logradouro;
    
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Logradouro getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getNumero() {
        return numero;
    }
    public void setNumero(Double numero) {
        this.numero = numero;
    }
    public Double getTelefone() {
        return telefone;
    }
    public void setTelefone(Double telefone) {
        this.telefone = telefone;
    }
    public Double getCpf() {
        return cpf;
    }
    public void setCpf(Double cpf) {
        this.cpf = cpf;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
}

