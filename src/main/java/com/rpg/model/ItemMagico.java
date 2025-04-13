package com.rpg.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemMagico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoItem tipo;

    @Column(nullable = false)
    private Integer forca;

    @Column(nullable = false)
    private Integer defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    public enum TipoItem {
        ARMA,
        ARMADURA,
        AMULETO
    }

    @PrePersist
    @PreUpdate
    public void validarAtributos() {
        if (tipo == TipoItem.ARMA) {
            defesa = 0;
        } else if (tipo == TipoItem.ARMADURA) {
            forca = 0;
        }
        
        if (forca == 0 && defesa == 0) {
            throw new IllegalArgumentException("O item não pode ter força e defesa zerados");
        }
        
        if (forca > 10 || defesa > 10) {
            throw new IllegalArgumentException("Força e defesa não podem ser maiores que 10");
        }
    }
} 