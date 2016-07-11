package com.jnutrition.backend;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlanModel {
    private final ObservableList<PlanItem> ingredients = FXCollections.observableArrayList();
    private final ObservableList<PlanItem> readOnly = FXCollections.unmodifiableObservableList(ingredients);

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

    public void addIngredient(double amount, Unit unit, Ingredient ingredient) {
        PlanItem item = new PlanItem(ingredient, amount, unit);
        double currentKcal = getKcal();
        double currentProtein = getProtein();
        double currentCarbs = getCarbs();
        double currentFat = getFat();

        kcal.set(item.getKcal() + currentKcal);
        protein.set(item.getProtein() + currentProtein);
        carbs.set(item.getCarbs() + currentCarbs);
        fat.set(item.getFat() + currentFat);
        ingredients.add(item);
    }

    public ObservableList<PlanItem> getReadOnlyList() {
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
