package com.jnutrition.DAO.jpa;

import com.jnutrition.DAO.PlanDAO;
import com.jnutrition.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JPAPlanDAO implements PlanDAO{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    DataBrocker dataBrocker;

    @Override
    public void savePlan(Plan plan) {
        dataBrocker.saveOrUpdate(plan);
    }

    @Override
    public void updatePlan(Plan plan) {
        dataBrocker.saveOrUpdate(plan);
    }

    @Override
    public Plan loadPlan(String name) {
        return dataBrocker.selectWherePropertyEquals(Plan.class, "name", name);
    }

    @Override
    public List<String> getAllPlanNames() {
        List<String> result = dataBrocker.selectAll(Plan.class).stream().map(Plan::getName).collect(Collectors.toList());
        return result;
    }
}
