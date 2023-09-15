package com.example.meusgastos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meusgastos.domain.dto.contato.ContatoRequestDTO;
import com.example.meusgastos.domain.dto.contato.ContatoResponseDTO;
import com.example.meusgastos.domain.service.ContatoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> obterTodos(){
        return ResponseEntity.ok(contatoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(contatoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> cadastrar(@RequestBody ContatoRequestDTO dto){
        ContatoResponseDTO contato = contatoService.cadastrar(dto);
        return new ResponseEntity<ContatoResponseDTO>(contato, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ContatoRequestDTO dto){
        ContatoResponseDTO contato = contatoService.atualizar(id, dto);
        return new ResponseEntity<ContatoResponseDTO>(contato, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        contatoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
