package com.fstt.store.beans;


import com.fstt.store.persistence.Client;
import com.fstt.store.utils.JpaUtil;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.io.Serializable;

@Named
@SessionScoped
@Data
public class ClientBean implements Serializable {
    private EntityManager em;
    private Client clientActuel;

    public ClientBean() {
        em = JpaUtil.getInstance().getEntityManager();
    }

    public boolean estConnecte() {
        return clientActuel != null;
    }


}
