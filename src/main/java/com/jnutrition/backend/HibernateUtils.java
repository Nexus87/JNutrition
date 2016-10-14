package com.jnutrition.backend;

import com.jnutrition.model.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class HibernateUtils {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManager() {
        if(entityManagerFactory == null)
            createEntityManager();
        return entityManagerFactory;
    }

    private static void createEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CRM");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Ingredient water = new Ingredient("Water", 1.0, 1.0, 1.0, 0.0);
        Ingredient egg = new Ingredient("Egg", 3.3, 2.2, 1.12, 3.0);
        Ingredient apple = new Ingredient("Apple", 3.0, 2.0, 1.0, 0.0);

        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Ingredient i").executeUpdate();
        entityManager.persist(water);
        entityManager.persist(egg);
        entityManager.persist(apple);
        entityManager.getTransaction().commit();
    }
}
