package com.example.meusgastos.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.meusgastos.domain.dto.cidade.CidadeRequestDTO;
import com.example.meusgastos.domain.dto.cidade.CidadeResponseDTO;
import com.example.meusgastos.domain.exception.BadRequestException;
import com.example.meusgastos.domain.exception.ResourceNotFoundException;
import com.example.meusgastos.domain.model.Cidade;
import com.example.meusgastos.domain.model.Estado;
import com.example.meusgastos.domain.repository.CidadeRepository;

@Service
public class CidadeService implements ICRUDService<CidadeRequestDTO, CidadeResponseDTO> {
@Autowired
private CidadeRepository cidadeRepository;
@Autowired
private ModelMapper mapper;

    @Override
    public List<CidadeResponseDTO> obterTodos() {
        Estado estado = (Estado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Cidade> cidades = cidadeRepository.findByEstado(estado);
        return cidades.stream().map(cidade -> mapper.map(cidade, CidadeResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CidadeResponseDTO obterPorId(Long id) {
        Optional<Cidade> optCidade = cidadeRepository.findById(id);
        if(optCidade.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar" + " o título com o id: " + id);
        }
        return mapper.map(optCidade.get(), CidadeResponseDTO.class);
    }

    @Override
    public CidadeResponseDTO cadastrar(CidadeRequestDTO dto) {
        validarCidade(dto);
        Cidade cidade = mapper.map(dto, Cidade.class);
        Estado estado = (Estado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cidade.setEstado(estado);
        cidade.setId(null);
        //cidade.setDataCadastro(new Date()); Aqui vai uma pergunta, precisa de data de cadastro?..
        cidade = cidadeRepository.save(cidade);
        return mapper.map(cidade, CidadeResponseDTO.class);
    }

    @Override
    public CidadeResponseDTO atualizar(Long id, CidadeRequestDTO dto) {
        CidadeResponseDTO cidadeBanco = obterPorId(id); //quando q era pra ser usado isso? Juroooooo
        validarCidade(dto);
        Cidade cidade = mapper.map(dto, Cidade.class);
        Estado estado = (Estado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cidade.setEstado(estado);
        cidade.setId(id);
        cidade = cidadeRepository.save(cidade);
        return mapper.map(cidade, CidadeResponseDTO.class);
    }

    //public List<CidadeResponseDTO> obterPorDataDeVencimento(String periodoInicial, String periodoFinal){
    //    List<cidade> cidades = cidadeRepository.obterFluxoCaixaPorDataVencimento(periodoInicial, periodoFinal);
    //    return cidades.stream()
    //    .map( -> mapper.map(cidade, CidadeResponseDTO.class))
    //    .collect(Collectors.toList());
    //}

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        cidadeRepository.deleteById(id);
    }
    
    //não sei precisa desse validar cidade?
    private void validarCidade(CidadeRequestDTO dto){
        if(dto.getNomeCidade() == null){
            throw new BadRequestException("cidade Inválido - Campos Obrigatórios");
        }
    }
}
