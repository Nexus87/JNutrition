package com.jnutrition.DAO;

import com.jnutrition.model.Ingredient;
import com.jnutrition.model.Unit;

import java.util.List;

public interface UnitDAO {
    void save(List<Unit> units);
    List<Unit> getUnitsForIngredient(Ingredient ingredient);
}
