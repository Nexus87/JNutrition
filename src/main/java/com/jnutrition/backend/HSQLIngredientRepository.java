package com.jnutrition.backend;

import com.jnutrition.model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HSQLIngredientRepository implements IngredientRepository, InitializingBean{
    private String cfgPath;
    private SessionFactory sessionFactory;
    private ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

    @Value(value = "${cfg.hibernate}")
    public void setCfgPath(String cfgPath) {
        this.cfgPath = cfgPath;
    }

    @Override
    public ObservableList<Ingredient> getAllIngredients() {
        ingredients.clear();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Ingredient.class);
        ingredients.addAll(criteria.list());
        session.getTransaction().commit();
        session.close();
        return ingredients;
    }

    @Override
    public void setNameFilter(String name) {
        ingredients.clear();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Ingredient.class);
        criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
        ingredients.addAll(criteria.list());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Object getIngredientByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ingredient.class);
        criteria.add(Restrictions.eq("name", name));
        Object result = criteria.list().stream().findFirst().orElse(null);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure(cfgPath);
            configuration.addAnnotatedClass(Ingredient.class);
            System.out.println("Hibernate Annotation Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Ingredient water = new Ingredient("Water", 1.0, 1.0, 1.0, 0.0);
        Ingredient egg = new Ingredient("Egg", 3.3, 2.2, 1.12, 3.0);
        Ingredient apple = new Ingredient("Apple", 3.0, 2.0, 1.0, 0.0);

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Ingredient").executeUpdate();
        session.save(water);
        session.save(egg);
        session.save(apple);
        session.getTransaction().commit();
    }
}
