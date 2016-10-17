package com.jnutrition.repositories;


import com.jnutrition.model.Ingredient;
import com.jnutrition.model.Unit;
import javafx.collections.ObservableList;

public interface UnitRepository {
    ObservableList<Unit> getUnitForIngredient(Ingredient ingredient);
    ObservableList<Unit> getUnitForIngredient(String ingredient);
}
