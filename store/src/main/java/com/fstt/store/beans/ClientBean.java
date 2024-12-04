package com.fstt.store.beans;


import com.fstt.store.persistence.Client;
import com.fstt.store.utils.JpaUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.io.Serializable;

@Named
@RequestScoped
@Data
public class ClientBean implements Serializable {
    private String nom;
    private String email;
    private String motDePasse;

    private EntityManager em;


    public ClientBean() {
        em = JpaUtil.getInstance().getEntityManager();
    }

    public void inscrire() {
        try {
            Client client = new Client();
            client.setNom(nom);
            client.setEmail(email);
            client.setMotDePasse(motDePasse);

            em.getTransaction().begin();
            em.persist(client);
            em.getTransaction().commit();
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),null,"listArticles.xhtml");

        } catch (Exception e) {
            em.getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Une erreur est survenue lors de l'inscription."));
            e.printStackTrace();
        }
    }



}
