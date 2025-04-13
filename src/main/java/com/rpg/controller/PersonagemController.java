package com.rpg.controller;

import com.rpg.dto.PersonagemDTO;
import com.rpg.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<PersonagemDTO> criarPersonagem(@RequestBody PersonagemDTO personagemDTO) {
        return ResponseEntity.ok(personagemService.criarPersonagem(personagemDTO));
    }

    @GetMapping
    public ResponseEntity<List<PersonagemDTO>> listarPersonagens() {
        return ResponseEntity.ok(personagemService.listarPersonagens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemDTO> buscarPersonagemPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.buscarPersonagemPorId(id));
    }

    @PatchMapping("/{id}/nome-aventureiro")
    public ResponseEntity<PersonagemDTO> atualizarNomeAventureiro(
            @PathVariable Long id,
            @RequestParam String novoNomeAventureiro) {
        return ResponseEntity.ok(personagemService.atualizarNomeAventureiro(id, novoNomeAventureiro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPersonagem(@PathVariable Long id) {
        personagemService.removerPersonagem(id);
        return ResponseEntity.noContent().build();
    }
} 