package com.jnutrition.backend;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlanModel {
    private final ObservableList<PlanItem> ingredients = FXCollections.observableArrayList();
    private final ObservableList<PlanItem> readOnly = FXCollections.unmodifiableObservableList(ingredients);

    public SimpleObjectProperty<BigDecimal> kcalProperty() {
        return kcal;
    }

    public SimpleObjectProperty<BigDecimal> proteinProperty() {
        return protein;
    }

    public SimpleObjectProperty<BigDecimal> carbsProperty() {
        return carbs;
    }

    public SimpleObjectProperty<BigDecimal> fatProperty() {
        return fat;
    }

    private final SimpleObjectProperty<BigDecimal> kcal = new SimpleObjectProperty<>(new BigDecimal(0));
    private final SimpleObjectProperty<BigDecimal> protein = new SimpleObjectProperty<>(new BigDecimal(0));
    private final SimpleObjectProperty<BigDecimal> carbs = new SimpleObjectProperty<>(new BigDecimal(0));
    private final SimpleObjectProperty<BigDecimal> fat = new SimpleObjectProperty<>(new BigDecimal(0));

    public void addIngredient(double amount, Unit unit, Ingredient ingredient) {
        PlanItem item = new PlanItem(ingredient, amount, unit);
        BigDecimal currentKcal = getKcal();
        BigDecimal currentProtein = getProtein();
        BigDecimal currentCarbs = getCarbs();
        BigDecimal currentFat = getFat();

        kcal.set(currentKcal.add(item.getKcal().setScale(2, RoundingMode.HALF_UP)));
        protein.set(currentProtein.add(item.getProtein()).setScale(2, RoundingMode.HALF_UP));
        carbs.set(currentCarbs.add(item.getCarbs()).setScale(2, RoundingMode.HALF_UP));
        fat.set(currentFat.add(item.getFat()).setScale(2, RoundingMode.HALF_UP));
        ingredients.add(item);
    }

    public ObservableList<PlanItem> getReadOnlyList() {
        return readOnly;
    }

    BigDecimal getKcal() {
        return kcal.get();
    }

    BigDecimal getProtein() {
        return protein.get();
    }

    BigDecimal getCarbs() {
        return carbs.get();
    }

    BigDecimal getFat() {
        return fat.get();
    }
}
