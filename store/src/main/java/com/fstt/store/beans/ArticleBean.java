package com.fstt.store.beans;

import com.fstt.store.persistence.Article;
import com.fstt.store.utils.JpaUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.util.List;


@Named
@RequestScoped
@Data
public class ArticleBean {

    private EntityManager em;

    private String nom;
    private String description;
    private double prix;
    private int stock;
    private String image;

    public ArticleBean() {
        // Obtention de l'EntityManager via JpaUtil
        em = JpaUtil.getInstance().getEntityManager();
    }

    //Méthode pour persister un Article
    public void ajouterArticle() {
       Article art = new Article();
       art.setNom(nom);
       art.setDescription(description);
       art.setStock(stock);
       art.setPrix(prix);
       art.setImage(image);

       em.getTransaction().begin();
        System.out.println("COMITING THE NEW ARTICLE");
        em.persist(art);
        em.getTransaction().commit();

        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),null,"listArticles.xhtml");
    }

    //Methode pour lister les article disponibles
    public List<Article> lister () {
        List<Article> mesArticles = em.createQuery(" select art from Article as art").getResultList();

        return mesArticles;
    }
}
