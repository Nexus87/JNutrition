package com.jnutrition.DAO.jpa;

import com.jnutrition.DAO.PlanDAO;
import com.jnutrition.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JPAPlanDAO implements PlanDAO{
    @Autowired
    DataBroker dataBroker;

    @Override
    public void savePlan(Plan plan) {
        dataBroker.saveOrUpdate(plan);
    }

    @Override
    public void updatePlan(Plan plan) {
        dataBroker.saveOrUpdate(plan);
    }

    @Override
    public Plan loadPlan(String name) {
        return dataBroker.selectWherePropertyEquals(Plan.class, "name", name);
    }

    @Override
    public List<String> getAllPlanNames() {
        return dataBroker.selectAll(Plan.class).stream().map(Plan::getName).collect(Collectors.toList());
    }
}
