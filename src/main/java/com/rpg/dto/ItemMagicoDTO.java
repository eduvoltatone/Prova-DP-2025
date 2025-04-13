package com.rpg.dto;

import com.rpg.model.ItemMagico;
import lombok.Data;

@Data
public class ItemMagicoDTO {
    private Long id;
    private String nome;
    private ItemMagico.TipoItem tipo;
    private Integer forca;
    private Integer defesa;
    private Long personagemId;

    public static ItemMagicoDTO fromEntity(ItemMagico item) {
        ItemMagicoDTO dto = new ItemMagicoDTO();
        dto.setId(item.getId());
        dto.setNome(item.getNome());
        dto.setTipo(item.getTipo());
        dto.setForca(item.getForca());
        dto.setDefesa(item.getDefesa());
        dto.setPersonagemId(item.getPersonagem() != null ? item.getPersonagem().getId() : null);
        return dto;
    }
} 