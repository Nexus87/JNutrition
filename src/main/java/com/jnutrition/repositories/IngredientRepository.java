package com.jnutrition.repositories;

import com.jnutrition.model.Ingredient;
import javafx.collections.ObservableList;

public interface IngredientRepository {

    ObservableList<Ingredient> getAllIngredients();
    void setNameFilter(String name);
    Object getIngredientByName(String name);

    void close();
}
