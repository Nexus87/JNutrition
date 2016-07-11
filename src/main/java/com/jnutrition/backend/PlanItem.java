package com.jnutrition.backend;

public class PlanItem {
    private final Ingredient ingredient;
    private final double amount;
    private final Unit unit;

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
    }

    public double getFat() {
        return ingredient.getFat();
    }
    public String getName() {
        return ingredient.getName();
    }
    public double getKcal() {
        return ingredient.getKcal();
    }
    public double getProtein() {
        return ingredient.getProtein();
    }
    public double getCarbs() {
        return ingredient.getCarbs();
    }
}
