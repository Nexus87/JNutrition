package com.jnutrition.backend;

import javafx.beans.property.*;

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

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof  Ingredient))
            return false;
        if(super.equals(obj))
            return  true;

        return equals((Ingredient) obj);
    }

    private final static double DELTA = 1e-15;

    public boolean equals(Ingredient other){
        return name.get().equals(other.name.get()) &&
                Math.abs(kcal.get() - other.kcal.get()) < DELTA &&
                Math.abs(protein.get() - other.protein.get()) < DELTA &&
                Math.abs(carbs.get() - other.carbs.get()) < DELTA &&
                Math.abs(fat.get() - other.fat.get()) < DELTA;
    }

    @Override
    public String toString() {
        return "[" + name.get() + ", " + kcal + ", " + protein + ", " + carbs + ", " + fat + "]";
    }
}
