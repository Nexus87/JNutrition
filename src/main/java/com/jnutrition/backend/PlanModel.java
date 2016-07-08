package com.jnutrition.backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlanModel {
    private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
    private final ObservableList<Ingredient> readOnly = FXCollections.unmodifiableObservableList(ingredients);

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public ObservableList<Ingredient> getReadOnlyList() {
        return readOnly;
    }
}
