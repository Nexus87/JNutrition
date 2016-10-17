package com.jnutrition.DAO.jpa;

import com.jnutrition.DAO.UnitDAO;
import com.jnutrition.model.Ingredient;
import com.jnutrition.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class JPAUnitDAO implements UnitDAO{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public void save(List<Unit> units) {

    }

    @Override
    public List<Unit> getUnitsForIngredient(Ingredient ingredient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Unit> c = cb.createQuery(Unit.class);
        Root<Unit> i = c.from(Unit.class);
        c.where(cb.equal(i.get("ingredient"), ingredient));
        List<Unit> resultList = entityManager.createQuery(c).getResultList();
        entityManager.close();
        return resultList;
    }
}
