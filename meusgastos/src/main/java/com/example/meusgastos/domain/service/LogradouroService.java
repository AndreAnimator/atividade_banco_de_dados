package com.example.meusgastos.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.meusgastos.domain.dto.logradouro.LogradouroRequestDTO;
import com.example.meusgastos.domain.dto.logradouro.LogradouroResponseDTO;
import com.example.meusgastos.domain.exception.BadRequestException;
import com.example.meusgastos.domain.exception.ResourceNotFoundException;
import com.example.meusgastos.domain.model.Cidade;
import com.example.meusgastos.domain.model.Estado;
import com.example.meusgastos.domain.model.Logradouro;
import com.example.meusgastos.domain.repository.LogradouroRepository;

@Service
public class LogradouroService implements ICRUDService<LogradouroRequestDTO, LogradouroResponseDTO> {
@Autowired
private LogradouroRepository logradouroRepository;
@Autowired
private ModelMapper mapper;

    @Override
    public List<LogradouroResponseDTO> obterTodos() {
        Cidade cidade = (Cidade) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Logradouro> logradouros = logradouroRepository.findByCidade(cidade);
        return logradouros.stream().map(logradouro -> mapper.map(logradouro, LogradouroResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LogradouroResponseDTO obterPorId(Long id) {
        Optional<Logradouro> optLogradouro = logradouroRepository.findById(id);
        if(optLogradouro.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar" + " o título com o id: " + id);
        }
        return mapper.map(optLogradouro.get(), LogradouroResponseDTO.class);
    }

    @Override
    public LogradouroResponseDTO cadastrar(LogradouroRequestDTO dto) {
        validarLogradouro(dto);
        Logradouro logradouro = mapper.map(dto, Logradouro.class);
        Cidade cidade = (Cidade) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logradouro.setCidade(cidade);
        logradouro.setId(null);
        //logradouro.setDataCadastro(new Date()); Aqui vai uma pergunta, precisa de data de cadastro?..
        logradouro = logradouroRepository.save(logradouro);
        return mapper.map(logradouro, LogradouroResponseDTO.class);
    }

    @Override
    public LogradouroResponseDTO atualizar(Long id, LogradouroRequestDTO dto) {
        LogradouroResponseDTO logradouroBanco = obterPorId(id); //quando q era pra ser usado isso? Juroooooo
        validarLogradouro(dto);
        Logradouro logradouro = mapper.map(dto, Logradouro.class);
        Cidade cidade = (Cidade) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logradouro.setCidade(cidade);
        logradouro.setId(id);
        logradouro = logradouroRepository.save(logradouro);
        return mapper.map(logradouro, LogradouroResponseDTO.class);
    }

    //public List<LogradouroResponseDTO> obterPorDataDeVencimento(String periodoInicial, String periodoFinal){
    //    List<logradouro> logradouros = logradouroRepository.obterFluxoCaixaPorDataVencimento(periodoInicial, periodoFinal);
    //    return logradouros.stream()
    //    .map( -> mapper.map(logradouro, LogradouroResponseDTO.class))
    //    .collect(Collectors.toList());
    //}

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        logradouroRepository.deleteById(id);
    }
    
    //não sei precisa desse validar logradouro?
    private void validarLogradouro(LogradouroRequestDTO dto){
        if(dto.getNomeRua() == null){
            throw new BadRequestException("logradouro Inválido - Campos Obrigatórios");
        }
    }
}
