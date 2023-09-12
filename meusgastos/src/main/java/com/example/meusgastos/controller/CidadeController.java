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

import com.example.meusgastos.domain.dto.cidade.CidadeRequestDTO;
import com.example.meusgastos.domain.dto.cidade.CidadeResponseDTO;
import com.example.meusgastos.domain.service.CidadeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cidades")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<CidadeResponseDTO>> obterTodos(){
        return ResponseEntity.ok(cidadeService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(cidadeService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<CidadeResponseDTO> cadastrar(@RequestBody CidadeRequestDTO dto){
        CidadeResponseDTO cidade = cidadeService.cadastrar(dto);
        return new ResponseEntity<CidadeResponseDTO>(cidade, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> atualizar(@PathVariable Long id, @RequestBody CidadeRequestDTO dto){
        CidadeResponseDTO cidade = cidadeService.atualizar(id, dto);
        return new ResponseEntity<CidadeResponseDTO>(cidade, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        cidadeService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
