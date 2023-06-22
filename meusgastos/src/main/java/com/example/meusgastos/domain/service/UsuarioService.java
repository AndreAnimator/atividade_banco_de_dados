package com.example.meusgastos.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.meusgastos.domain.dto.UsuarioRequestDTO;
import com.example.meusgastos.domain.dto.UsuarioResponseDTO;

@Service
public class UsuarioService implements ICRUDService<UsuarioRequestDTO, UsuarioResponseDTO>{

    @Override
    public List<UsuarioResponseDTO> obterTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @Override
    public UsuarioResponseDTO obterPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPorId'");
    }

    @Override
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
    
}
