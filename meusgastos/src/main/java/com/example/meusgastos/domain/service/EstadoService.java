package com.example.meusgastos.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.meusgastos.domain.dto.estado.EstadoRequestDTO;
import com.example.meusgastos.domain.dto.estado.EstadoResponseDTO;
import com.example.meusgastos.domain.exception.BadRequestException;
import com.example.meusgastos.domain.exception.ResourceNotFoundException;
import com.example.meusgastos.domain.model.Estado;
import com.example.meusgastos.domain.repository.EstadoRepository;

@Service
public class EstadoService implements ICRUDService<EstadoRequestDTO, EstadoResponseDTO> {
@Autowired
private EstadoRepository estadoRepository;
@Autowired
private ModelMapper mapper;

    @Override
    public List<EstadoResponseDTO> obterTodos() {
        List<Estado> estados = estadoRepository.findAll();
        return estados.stream().map(estado -> mapper.map(estado, EstadoResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EstadoResponseDTO obterPorId(Long id) {
        Optional<Estado> optEstado = estadoRepository.findById(id);
        if(optEstado.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar" + " o título com o id: " + id);
        }
        return mapper.map(optEstado.get(), EstadoResponseDTO.class);
    }

    @Override
    public EstadoResponseDTO cadastrar(EstadoRequestDTO dto) {
        validarEstado(dto);
        Estado estado = mapper.map(dto, Estado.class);
        //precisa de mais coisa pro estado?
        estado = estadoRepository.save(estado);
        return mapper.map(estado, EstadoResponseDTO.class);
    }

    @Override
    public EstadoResponseDTO atualizar(Long id, EstadoRequestDTO dto) {
        EstadoResponseDTO estadoBanco = obterPorId(id); //quando q era pra ser usado isso? Juroooooo
        validarEstado(dto);
        Estado estado = mapper.map(dto, Estado.class);
        //acho que falta coisa aqui...
        estado.setId(id);
        estado = estadoRepository.save(estado);
        return mapper.map(estado, EstadoResponseDTO.class);
    }

    //public List<EstadoResponseDTO> obterPorDataDeVencimento(String periodoInicial, String periodoFinal){
    //    List<estado> estados = estadoRepository.obterFluxoCaixaPorDataVencimento(periodoInicial, periodoFinal);
    //    return estados.stream()
    //    .map( -> mapper.map(estado, EstadoResponseDTO.class))
    //    .collect(Collectors.toList());
    //}

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        estadoRepository.deleteById(id);
    }
    
    //não sei precisa desse validar estado?
    private void validarEstado(EstadoRequestDTO dto){
        if(dto.getNomeEstado() == null){
            throw new BadRequestException("estado Inválido - Campos Obrigatórios");
        }
    }
}
