package com.jnutrition.backend;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlanModel {
    private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
    private final ObservableList<Ingredient> readOnly = FXCollections.unmodifiableObservableList(ingredients);

    public DoubleProperty kcalProperty() {
        return kcal;
    }

    public DoubleProperty proteinProperty() {
        return protein;
    }

    public DoubleProperty carbsProperty() {
        return carbs;
    }

    public DoubleProperty fatProperty() {
        return fat;
    }

    private DoubleProperty kcal = new SimpleDoubleProperty();
    private DoubleProperty protein = new SimpleDoubleProperty();
    private DoubleProperty carbs = new SimpleDoubleProperty();
    private DoubleProperty fat = new SimpleDoubleProperty();

    public void addIngredient(Ingredient ingredient) {
        double currentKcal = getKcal();
        double currentProtein = getProtein();
        double currentCarbs = getCarbs();
        double currentFat = getFat();

        kcal.set(ingredient.getKcal() + currentKcal);
        protein.set(ingredient.getProtein() + currentProtein);
        carbs.set(ingredient.getCarbs() + currentCarbs);
        fat.set(ingredient.getFat() + currentFat);
        ingredients.add(ingredient);
    }

    public ObservableList<Ingredient> getReadOnlyList() {
        return readOnly;
    }

    public double getKcal() {
        return kcal.get();
    }

    public double getProtein() {
        return protein.get();
    }

    public double getCarbs() {
        return carbs.get();
    }

    public double getFat() {
        return fat.get();
    }
}
