package com.rpg.service;

import com.rpg.dto.PersonagemDTO;
import com.rpg.model.Personagem;
import com.rpg.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Transactional
    public PersonagemDTO criarPersonagem(PersonagemDTO personagemDTO) {
        if (personagemDTO.getForca() + personagemDTO.getDefesa() != 10) {
            throw new IllegalArgumentException("A soma de força e defesa deve ser igual a 10");
        }

        Personagem personagem = new Personagem();
        personagem.setNome(personagemDTO.getNome());
        personagem.setNomeAventureiro(personagemDTO.getNomeAventureiro());
        personagem.setClasse(personagemDTO.getClasse());
        personagem.setLevel(personagemDTO.getLevel());
        personagem.setForca(personagemDTO.getForca());
        personagem.setDefesa(personagemDTO.getDefesa());

        personagem = personagemRepository.save(personagem);
        return PersonagemDTO.fromEntity(personagem);
    }

    public List<PersonagemDTO> listarPersonagens() {
        return personagemRepository.findAll().stream()
                .map(PersonagemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public PersonagemDTO buscarPersonagemPorId(Long id) {
        return personagemRepository.findById(id)
                .map(PersonagemDTO::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
    }

    @Transactional
    public PersonagemDTO atualizarNomeAventureiro(Long id, String novoNomeAventureiro) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        personagem.setNomeAventureiro(novoNomeAventureiro);
        personagem = personagemRepository.save(personagem);
        return PersonagemDTO.fromEntity(personagem);
    }

    @Transactional
    public void removerPersonagem(Long id) {
        personagemRepository.deleteById(id);
    }
} 