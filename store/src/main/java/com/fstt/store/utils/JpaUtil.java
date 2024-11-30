package com.fstt.store.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static JpaUtil instance; //Instance unique de JpaUtil
    private EntityManagerFactory emf;

    //constructeur privé pour empecher l'isntanciation directe
    private JpaUtil() {
        emf = Persistence.createEntityManagerFactory("default");
    }

    //Méthode pour obtenir l'instance unique
    public static JpaUtil getInstance() {
       if (instance == null){
           synchronized (JpaUtil.class){
               if (instance == null) {
                   instance = new JpaUtil();
               }
           }
       }
        return instance;
    }

    //Fournit un EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //Ferme l'EntityManagerFactory à la fin de l'application
    public void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
