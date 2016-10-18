package com.jnutrition.DAO;

import com.jnutrition.model.Plan;

import java.util.List;

public interface PlanDAO {
    void savePlan(Plan plan);
    void updatePlan(Plan plan);
    Plan loadPlan(String name);
    List<String> getAllPlanNames();
}
