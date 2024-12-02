package com.fstt.store.beans;

import com.fstt.store.persistence.Article;
import com.fstt.store.utils.JpaUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Part;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
    private Part image;

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

       //Hnadle file upload
        if (image != null) {
            try {
                String fileName = image.getSubmittedFileName();
                String uploadDir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/uploads");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                File file = new File(uploadDirFile,fileName);
                Files.copy(image.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                art.setImage("/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
