package com.fstt.store.persistence;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private int stock;
    private String image;

    @ManyToOne
    private Panier panier;
}
