package com.fstt.store.beans;

import com.fstt.store.persistence.Article;
import com.fstt.store.persistence.Panier;
import com.fstt.store.utils.JpaUtil;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Data
public class PanierBean implements Serializable {

    private EntityManager em;
    private Panier panier = new Panier();

    private List<Article> articlespanier = new ArrayList<>();

    public PanierBean() {
        // Obtention de l'EntityManager via JpaUtil
        em = JpaUtil.getInstance().getEntityManager();
    }

    public void ajouterAuPanier(Article article) {
        System.out.println("ajouter au panier est appelée");
        articlespanier.add(article);
        panier.setArticles(articlespanier);

        em.getTransaction().begin();
        System.out.println("Article ajouteé au panier");
        em.persist(panier);
        em.getTransaction().commit();
    }

    public List<Article> getPanier() {
        return articlespanier;
    }

}