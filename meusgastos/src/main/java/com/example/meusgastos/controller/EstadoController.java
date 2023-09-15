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

import com.example.meusgastos.domain.dto.estado.EstadoRequestDTO;
import com.example.meusgastos.domain.dto.estado.EstadoResponseDTO;
import com.example.meusgastos.domain.service.EstadoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/estados")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<EstadoResponseDTO>> obterTodos(){
        return ResponseEntity.ok(estadoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(estadoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<EstadoResponseDTO> cadastrar(@RequestBody EstadoRequestDTO dto){
        EstadoResponseDTO estado = estadoService.cadastrar(dto);
        return new ResponseEntity<EstadoResponseDTO>(estado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoResponseDTO> atualizar(@PathVariable Long id, @RequestBody EstadoRequestDTO dto){
        EstadoResponseDTO estado = estadoService.atualizar(id, dto);
        return new ResponseEntity<EstadoResponseDTO>(estado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        estadoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
