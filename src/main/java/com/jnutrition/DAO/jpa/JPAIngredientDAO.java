package com.jnutrition.DAO.jpa;

import com.jnutrition.DAO.IngredientDAO;
import com.jnutrition.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class JPAIngredientDAO implements IngredientDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(List<Ingredient> ingredientList) {

    }

    @Override
    public List<Ingredient> getAllIngredients() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ingredient> c = cb.createQuery(Ingredient.class);
        Root<Ingredient> i = c.from(Ingredient.class);

        c.select(i);
        List<Ingredient> resultList = em.createQuery(c).getResultList();
        em.close();
        return resultList;
    }

    @Override
    public List<Ingredient> getIngredientsWihtNameLike(String filter) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ingredient> c = cb.createQuery(Ingredient.class);
        Root<Ingredient> i = c.from(Ingredient.class);
        c.where(cb.like(cb.lower(i.get("name")), "%" + filter.toLowerCase() + "%"));

        List<Ingredient> resultList = em.createQuery(c).getResultList();
        em.close();
        return resultList;
    }
}
