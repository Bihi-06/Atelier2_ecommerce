package com.fstt.store.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();
}
