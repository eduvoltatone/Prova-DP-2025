package com.rpg.repository;

import com.rpg.model.ItemMagico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Long> {
    List<ItemMagico> findByPersonagemId(Long personagemId);
    ItemMagico findByPersonagemIdAndTipo(Long personagemId, ItemMagico.TipoItem tipo);
} 