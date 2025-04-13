package com.rpg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Classe classe;

    @Column(nullable = false)
    private Integer level = 1;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private List<ItemMagico> itensMagicos;

    @Column(nullable = false)
    private Integer forca;

    @Column(nullable = false)
    private Integer defesa;

    public enum Classe {
        GUERREIRO,
        MAGO,
        ARQUEIRO,
        LADINO,
        BARDO
    }

    public Integer getForcaTotal() {
        return forca + (itensMagicos != null ? itensMagicos.stream()
                .mapToInt(ItemMagico::getForca)
                .sum() : 0);
    }

    public Integer getDefesaTotal() {
        return defesa + (itensMagicos != null ? itensMagicos.stream()
                .mapToInt(ItemMagico::getDefesa)
                .sum() : 0);
    }
} 