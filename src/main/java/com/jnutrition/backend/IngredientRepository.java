package com.jnutrition.backend;

import javafx.collections.ObservableList;

public interface IngredientRepository {

    ObservableList<Ingredient> getAllIngredients();
    void setNameFilter(String name);
    Object getIngredientByName(String name);
}
