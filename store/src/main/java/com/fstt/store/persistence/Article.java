package com.fstt.store.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Article implements Serializable { //pour pouvoir passer l'objet Article entre les vues,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private int stock;
    private String image;

}
