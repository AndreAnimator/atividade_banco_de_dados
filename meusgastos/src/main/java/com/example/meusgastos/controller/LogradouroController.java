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

import com.example.meusgastos.domain.dto.logradouro.LogradouroRequestDTO;
import com.example.meusgastos.domain.dto.logradouro.LogradouroResponseDTO;
import com.example.meusgastos.domain.service.LogradouroService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/logradouros")
public class LogradouroController {
    @Autowired
    private LogradouroService logradouroService;

    @GetMapping
    public ResponseEntity<List<LogradouroResponseDTO>> obterTodos(){
        return ResponseEntity.ok(logradouroService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogradouroResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(logradouroService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<LogradouroResponseDTO> cadastrar(@RequestBody LogradouroRequestDTO dto){
        LogradouroResponseDTO logradouro = logradouroService.cadastrar(dto);
        return new ResponseEntity<LogradouroResponseDTO>(logradouro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogradouroResponseDTO> atualizar(@PathVariable Long id, @RequestBody LogradouroRequestDTO dto){
        LogradouroResponseDTO logradouro = logradouroService.atualizar(id, dto);
        return new ResponseEntity<LogradouroResponseDTO>(logradouro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        logradouroService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
