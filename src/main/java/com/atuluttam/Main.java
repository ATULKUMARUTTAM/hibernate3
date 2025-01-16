package com.atuluttam;

import com.atuluttam.config.CustomPersistenceUnitInfo;
import com.atuluttam.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // Step 1: Create the EntityManagerFactory using the programmatic config
      EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());

        // Step 2: Create the EntityManager
        EntityManager em = emf.createEntityManager();

        // Step 3: Perform CRUD operation (Create in this case)
        em.getTransaction().begin();

        Product p = new Product();
        p.setId(101L);
        p.setName("Laptop");

        // Persist the Product object
        em.persist(p);

        // Commit the transaction
        em.getTransaction().commit();

        System.out.println("Product saved successfully.");

        // Step 4: Close the EntityManager and EntityManagerFactory
        em.close();
        emf.close();
    }
}
