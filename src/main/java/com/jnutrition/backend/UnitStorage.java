package com.jnutrition.backend;

/**
 * Created by nexxuz0 on 22.04.2016.
 */
public interface UnitStorage {
    void AddUnitForIngredient(String ingredientName, Unit unit);
    void MergeUnitForIngredient(String ingredientName, Unit unit);
    void RemoveUnitForIngredient(String ingredientName, String unitName);
}
