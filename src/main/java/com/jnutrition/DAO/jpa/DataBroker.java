package com.jnutrition.DAO.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class DataBroker {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public <T> List<T> selectAll(Class<T> c){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(c);

        List<T> result = entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(c))).getResultList();
        entityManager.close();

        return result;

    }

    public <T> void saveOrUpdate(T data){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(data);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public <T> T selectWherePropertyEquals(Class<T> c, String property, String value){
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<T> query = buildQuery(em, c, (cb, i) -> cb.equal(cb.lower(i.get(property)), value.toLowerCase()));
        T result = query.getSingleResult();
        em.close();

        return result;
    }

    public <T> List<T> selectWherePropertyLike(Class<T> c, String property, String value){
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<T> query = buildQuery(em, c, (criteriaBuilder, root) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(property)), value.toLowerCase()));
        List<T> result = query.getResultList();
        em.close();

        return result;
    }

    public <T> TypedQuery<T> buildQuery(EntityManager em, Class<T> c, BiFunction<CriteriaBuilder, Root<T>, Predicate> filter){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(c);
        Root<T> i = criteriaQuery.from(c);
        criteriaQuery.where(filter.apply(cb, i));

        return  em.createQuery(criteriaQuery);
    }
}
