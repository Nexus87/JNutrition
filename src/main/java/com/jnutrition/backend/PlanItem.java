package com.jnutrition.backend;

public class PlanItem {
    private final Ingredient ingredient;
    private final double amount;
    private final Unit unit;
    private final double kcal;
    private final double protein;
    private final double carbs;
    private final double fat;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public Unit getUnit() {
        return unit;
    }

    PlanItem(Ingredient ingredient, double amount, Unit unit) {

        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;

        double factor = amount * unit.inGram() / 100;
        kcal = factor * ingredient.getKcal();
        protein = factor * ingredient.getProtein();
        carbs = factor * ingredient.getCarbs();
        fat = factor * ingredient.getFat();
    }

    public double getFat() {
        return fat;
    }
    public String getName() {
        return ingredient.getName();
    }
    public double getKcal() {
        return kcal;
    }
    public double getProtein() {
        return protein;
    }
    public double getCarbs() {
        return carbs;
    }
}
