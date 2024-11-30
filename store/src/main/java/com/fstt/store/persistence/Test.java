package com.fstt.store.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {

    public static void main(String[] args){

        Client client = new Client();
        client.setNom("ayoub");
        client.setEmail("ayoubaitsaid38@gmail.com");
        client.setMotDePasse("ayoub123");

        Article article1 = new Article();
        article1.setNom("AirPods");
        article1.setDescription("Original akhuya mn Apple l3ndek");
        article1.setPrix(200);
        article1.setStock(75);
        article1.setImage("walo");


        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("COMITING");
        em.persist(client);
        em.persist(article1);
        em.getTransaction().commit();
    }
}
