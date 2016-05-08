package com.jnutrition.backend;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by nexxuz01 on 22.04.2016.
 */
public class Ingredient {
    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty kcal = new SimpleDoubleProperty();
    private final DoubleProperty protein = new SimpleDoubleProperty();
    private final DoubleProperty carbs = new SimpleDoubleProperty();
    private final DoubleProperty fat = new SimpleDoubleProperty();

    public Ingredient(String name, double kcal, double protein, double carbs, double fat) {
        this.name.set(name);
        this.kcal.set(kcal) ;
        this.protein.set(protein);
        this.carbs.set(carbs);
        this.fat.set(fat);
    }

    public double getCarbs() {
        return carbs.get();
    }

    public double getProtein() {
        return protein.get();
    }

    public double getKcal() {
        return kcal.get();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty kcalProperty() {
        return kcal;
    }

    public DoubleProperty proteinProperty() {
        return protein;
    }

    public DoubleProperty carbsProperty() {
        return carbs;
    }

    public double getFat() {
        return fat.get();
    }

    public DoubleProperty fatProperty() {
        return fat;
    }
}
