package com.jnutrition.DAO.jpa;

import com.jnutrition.DAO.PlanDAO;
import com.jnutrition.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JPAPlanDAO implements PlanDAO{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public void savePlan(Plan plan) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(plan);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updatePlan(Plan plan) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(plan);
        entityManager.getTransaction().commit();
    }

    @Override
    public Plan loadPlan(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Plan> c = cb.createQuery(Plan.class);
        Root<Plan> i = c.from(Plan.class);
        c.where(cb.equal(cb.lower(i.get("name")), name.toLowerCase()));

        Plan result = em.createQuery(c).getSingleResult();
        em.close();
        return result;
    }

    @Override
    public List<String> getAllPlanNames() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Plan> c = cb.createQuery(Plan.class);
        Root<Plan> i = c.from(Plan.class);
        c.select(i.get("name"));

        List<String> result = em.createQuery(c).getResultList().stream().map(Plan::getName).collect(Collectors.toList());
        em.close();
        return result;
    }
}
