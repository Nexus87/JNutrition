package com.jnutrition.backend;


import javafx.collections.ObservableList;

public interface UnitRepository {
    ObservableList<Unit> getUnitForIngredient(Ingredient ingredient);
    ObservableList<Unit> getUnitForIngredient(String ingredient);
}
