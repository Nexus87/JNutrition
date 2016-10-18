package com.jnutrition.repositories;

import com.jnutrition.model.Plan;

public interface PlanRepository {
    Plan getPlanByName(String name);
    void savePlan(Plan plan);
}
