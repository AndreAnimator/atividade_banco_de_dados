package com.example.meusgastos.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTitulo")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double numero;

    @Column(nullable = false)
    private Double telefone;

    @Column(nullable = false)
    private Double cpf;

    @ManyToOne
    @JoinColumn(name = "idUsuario") //Um usuário pode ter vários contatos né... Meio pensado de última hora
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "idLogradouro")
    private Logradouro logradouro;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    
}
