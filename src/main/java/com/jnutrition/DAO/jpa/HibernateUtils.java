package com.jnutrition.DAO.jpa;

import com.jnutrition.model.Ingredient;
import com.jnutrition.model.Plan;
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

        try {
            Ingredient water = new Ingredient("Water", 1.0, 1.0, 1.0, 0.0);
            Ingredient egg = new Ingredient("Egg", 3.3, 2.2, 1.12, 3.0);
            Ingredient apple = new Ingredient("Apple", 3.0, 2.0, 1.0, 0.0);

            Unit applePound = new Unit("Pound", 50);
            Unit waterPound = new Unit("Pound", 50);
            Unit eggPound = new Unit("Pound", 50);
            Unit waterLiter = new Unit("Liter", 1);

            apple.getUnits().add(applePound);
            water.getUnits().add(waterPound);
            water.getUnits().add(waterLiter);
            egg.getUnits().add(eggPound);

            entityManager.getTransaction().begin();
            entityManager.persist(water);
            entityManager.persist(egg);
            entityManager.persist(apple);
            entityManager.getTransaction().commit();

            Plan plan = new Plan();
            plan.setName("Plan");
            entityManager.getTransaction().begin();
            entityManager.persist(plan);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (Exception e) {
            //Will throw an exception if the table was already filled.
        }
    }
}
