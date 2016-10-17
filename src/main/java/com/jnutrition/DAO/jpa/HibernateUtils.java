package com.jnutrition.DAO.jpa;

import com.jnutrition.model.Ingredient;
import com.jnutrition.model.Unit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
class HibernateUtils{

    private static EntityManagerFactory entityManagerFactory;

    @Bean
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
        entityManager.createQuery("DELETE FROM Unit u").executeUpdate();
        entityManager.createQuery("DELETE FROM Ingredient i").executeUpdate();
        entityManager.persist(water);
        entityManager.persist(egg);
        entityManager.persist(apple);
        entityManager.getTransaction().commit();

        Unit applePound = new Unit("Pound", 50, apple);
        Unit waterPound = new Unit("Pound", 50, water);
        Unit eggPound = new Unit("Pound", 50, egg);
        Unit waterLiter = new Unit("Liter", 1, water);
        entityManager.getTransaction().begin();
        entityManager.persist(applePound);
        entityManager.persist(waterPound);
        entityManager.persist(eggPound);
        entityManager.persist(waterLiter);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
