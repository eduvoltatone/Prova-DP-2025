package com.rpg.service;

import com.rpg.dto.ItemMagicoDTO;
import com.rpg.model.ItemMagico;
import com.rpg.model.Personagem;
import com.rpg.repository.ItemMagicoRepository;
import com.rpg.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @Transactional
    public ItemMagicoDTO criarItemMagico(ItemMagicoDTO itemMagicoDTO) {
        ItemMagico item = new ItemMagico();
        item.setNome(itemMagicoDTO.getNome());
        item.setTipo(itemMagicoDTO.getTipo());
        item.setForca(itemMagicoDTO.getForca());
        item.setDefesa(itemMagicoDTO.getDefesa());

        if (itemMagicoDTO.getPersonagemId() != null) {
            Personagem personagem = personagemRepository.findById(itemMagicoDTO.getPersonagemId())
                    .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
            
            if (item.getTipo() == ItemMagico.TipoItem.AMULETO) {
                ItemMagico amuletoExistente = itemMagicoRepository.findByPersonagemIdAndTipo(
                        personagem.getId(), ItemMagico.TipoItem.AMULETO);
                if (amuletoExistente != null) {
                    throw new IllegalArgumentException("O personagem já possui um amuleto");
                }
            }
            
            item.setPersonagem(personagem);
        }

        item = itemMagicoRepository.save(item);
        return ItemMagicoDTO.fromEntity(item);
    }

    public List<ItemMagicoDTO> listarItensMagicos() {
        return itemMagicoRepository.findAll().stream()
                .map(ItemMagicoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ItemMagicoDTO buscarItemMagicoPorId(Long id) {
        return itemMagicoRepository.findById(id)
                .map(ItemMagicoDTO::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Item mágico não encontrado"));
    }

    public List<ItemMagicoDTO> listarItensMagicosPorPersonagem(Long personagemId) {
        return itemMagicoRepository.findByPersonagemId(personagemId).stream()
                .map(ItemMagicoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ItemMagicoDTO buscarAmuletoDoPersonagem(Long personagemId) {
        ItemMagico amuleto = itemMagicoRepository.findByPersonagemIdAndTipo(
                personagemId, ItemMagico.TipoItem.AMULETO);
        if (amuleto == null) {
            throw new IllegalArgumentException("O personagem não possui um amuleto");
        }
        return ItemMagicoDTO.fromEntity(amuleto);
    }

    @Transactional
    public void removerItemMagico(Long id) {
        itemMagicoRepository.deleteById(id);
    }
} 