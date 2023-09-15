package com.example.meusgastos.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.meusgastos.domain.dto.contato.ContatoRequestDTO;
import com.example.meusgastos.domain.dto.contato.ContatoResponseDTO;
import com.example.meusgastos.domain.exception.BadRequestException;
import com.example.meusgastos.domain.exception.ResourceNotFoundException;
import com.example.meusgastos.domain.model.Contato;
import com.example.meusgastos.domain.model.Usuario;
import com.example.meusgastos.domain.repository.ContatoRepository;

@Service
public class ContatoService implements ICRUDService<ContatoRequestDTO, ContatoResponseDTO> {
@Autowired
private ContatoRepository contatoRepository;
@Autowired
private ModelMapper mapper;

    @Override
    public List<ContatoResponseDTO> obterTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Contato> contatos = contatoRepository.findByUsuario(usuario);
        return contatos.stream().map(contato -> mapper.map(contato, ContatoResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ContatoResponseDTO obterPorId(Long id) {
        Optional<Contato> optContato = contatoRepository.findById(id);
        if(optContato.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar" + " o título com o id: " + id);
        }
        return mapper.map(optContato.get(), ContatoResponseDTO.class);
    }

    @Override
    public ContatoResponseDTO cadastrar(ContatoRequestDTO dto) {
        validarContato(dto);
        Contato contato = mapper.map(dto, Contato.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contato.setUsuario(usuario);
        contato.setId(null);
        //contato.setDataCadastro(new Date()); Aqui vai uma pergunta, precisa de data de cadastro?..
        contato = contatoRepository.save(contato);
        return mapper.map(contato, ContatoResponseDTO.class);
    }

    @Override
    public ContatoResponseDTO atualizar(Long id, ContatoRequestDTO dto) {
        ContatoResponseDTO contatoBanco = obterPorId(id); //quando q era pra ser usado isso?
        validarContato(dto);
        Contato contato = mapper.map(dto, Contato.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contato.setUsuario(usuario);
        contato.setId(id);
        contato = contatoRepository.save(contato);
        return mapper.map(contato, ContatoResponseDTO.class);
    }

    //public List<ContatoResponseDTO> obterPorDataDeVencimento(String periodoInicial, String periodoFinal){
    //    List<Contato> contatos = contatoRepository.obterFluxoCaixaPorDataVencimento(periodoInicial, periodoFinal);
    //    return contatos.stream()
    //    .map( -> mapper.map(contato, ContatoResponseDTO.class))
    //    .collect(Collectors.toList());
    //}

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        contatoRepository.deleteById(id);
    }
    
    //não sei precisa desse validar contato?
    private void validarContato(ContatoRequestDTO dto){
        if(dto.getNome() == null || dto.getCpf() == null){
            throw new BadRequestException("Contato Inválido - Campos Obrigatórios");
        }
    }
}
