package com.rpg.dto;

import com.rpg.model.Personagem;
import lombok.Data;

import java.util.List;

@Data
public class PersonagemDTO {
    private Long id;
    private String nome;
    private String nomeAventureiro;
    private Personagem.Classe classe;
    private Integer level;
    private Integer forca;
    private Integer defesa;
    private Integer forcaTotal;
    private Integer defesaTotal;
    private List<ItemMagicoDTO> itensMagicos;

    public static PersonagemDTO fromEntity(Personagem personagem) {
        PersonagemDTO dto = new PersonagemDTO();
        dto.setId(personagem.getId());
        dto.setNome(personagem.getNome());
        dto.setNomeAventureiro(personagem.getNomeAventureiro());
        dto.setClasse(personagem.getClasse());
        dto.setLevel(personagem.getLevel());
        dto.setForca(personagem.getForca());
        dto.setDefesa(personagem.getDefesa());
        dto.setForcaTotal(personagem.getForcaTotal());
        dto.setDefesaTotal(personagem.getDefesaTotal());
        return dto;
    }
} 