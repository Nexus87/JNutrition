package com.jnutrition.repositories.jpa;

import com.jnutrition.DAO.PlanDAO;
import com.jnutrition.model.Plan;
import com.jnutrition.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JPAPlanRepository implements PlanRepository {
    @Autowired
    PlanDAO planDAO;

    @Override
    public Plan getPlanByName(String name) {
        return planDAO.loadPlan(name);
    }

    @Override
    public void savePlan(Plan plan) {
        planDAO.updatePlan(plan);
    }
}
