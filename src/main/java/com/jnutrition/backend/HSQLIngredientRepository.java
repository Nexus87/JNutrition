package com.jnutrition.backend;

import com.jnutrition.model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

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
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ingredient.class);
        ingredients.addAll(criteria.list());

        return ingredients;
    }

    @Override
    public void setNameFilter(String name) {
        ingredients.clear();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ingredient.class);
        criteria.add(Restrictions.eq("name", "%" + name + "%"));
        ingredients.addAll(criteria.list());
    }

    @Override
    public Object getIngredientByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ingredient.class);
        criteria.add(Restrictions.eq("name", name));
        return criteria.list().stream().findFirst().orElse(null);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure("hibernate-annotation.cfg.xml");
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
    }
}
