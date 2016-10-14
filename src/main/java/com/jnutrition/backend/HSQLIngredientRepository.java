package com.jnutrition.backend;

import com.jnutrition.model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class HSQLIngredientRepository implements IngredientRepository, InitializingBean{
    private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
    private EntityManagerFactory entityManagerFactory;

    @Override
    public ObservableList<Ingredient> getAllIngredients() {
        ingredients.clear();

        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ingredient> c = cb.createQuery(Ingredient.class);
        Root<Ingredient> i = c.from(Ingredient.class);

        c.select(i);
        List<Ingredient> resultList = em.createQuery(c).getResultList();
        ingredients.addAll(resultList);
        return ingredients;
    }

    @Override
    public void setNameFilter(String name) {
        ingredients.clear();

        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ingredient> c = cb.createQuery(Ingredient.class);
        Root<Ingredient> i = c.from(Ingredient.class);
        c.where(cb.like(cb.lower(i.get("name")), "%" + name.toLowerCase() + "%"));

        List<Ingredient> resultList = em.createQuery(c).getResultList();

        ingredients.addAll(resultList);
    }

    @Override
    public Object getIngredientByName(String name) {
        return new Object();
    }

    @Override
    public void close() {
        entityManagerFactory.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        entityManagerFactory = HibernateUtils.getEntityManager();
    }
}
