package com.rpg.controller;

import com.rpg.dto.ItemMagicoDTO;
import com.rpg.service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-magicos")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    @PostMapping
    public ResponseEntity<ItemMagicoDTO> criarItemMagico(@RequestBody ItemMagicoDTO itemMagicoDTO) {
        return ResponseEntity.ok(itemMagicoService.criarItemMagico(itemMagicoDTO));
    }

    @GetMapping
    public ResponseEntity<List<ItemMagicoDTO>> listarItensMagicos() {
        return ResponseEntity.ok(itemMagicoService.listarItensMagicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagicoDTO> buscarItemMagicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemMagicoService.buscarItemMagicoPorId(id));
    }

    @GetMapping("/personagem/{personagemId}")
    public ResponseEntity<List<ItemMagicoDTO>> listarItensMagicosPorPersonagem(
            @PathVariable Long personagemId) {
        return ResponseEntity.ok(itemMagicoService.listarItensMagicosPorPersonagem(personagemId));
    }

    @GetMapping("/personagem/{personagemId}/amuleto")
    public ResponseEntity<ItemMagicoDTO> buscarAmuletoDoPersonagem(
            @PathVariable Long personagemId) {
        return ResponseEntity.ok(itemMagicoService.buscarAmuletoDoPersonagem(personagemId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerItemMagico(@PathVariable Long id) {
        itemMagicoService.removerItemMagico(id);
        return ResponseEntity.noContent().build();
    }
} 