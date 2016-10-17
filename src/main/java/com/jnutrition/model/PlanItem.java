package com.jnutrition.model;

import java.math.BigDecimal;

public class PlanItem {
    private final Ingredient ingredient;
    private final double amount;
    private final Unit unit;
    private final BigDecimal kcal;
    private final BigDecimal protein;
    private final BigDecimal carbs;
    private final BigDecimal fat;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public PlanItem(Ingredient ingredient, double amount, Unit unit) {

        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;

        BigDecimal factor = new BigDecimal(amount * unit.inGram() / 100);
        kcal = ingredient.getKcal().multiply(factor).setScale(2, BigDecimal.ROUND_HALF_UP);
        protein = ingredient.getProtein().multiply(factor).setScale(2, BigDecimal.ROUND_HALF_UP);
        carbs = ingredient.getCarbs().multiply(factor).setScale(2, BigDecimal.ROUND_HALF_UP);
        fat = ingredient.getFat().multiply(factor).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getFat() {
        return fat;
    }
    public String getName() {
        return ingredient.getName();
    }
    public BigDecimal getKcal() {
        return kcal;
    }
    public BigDecimal getProtein() {
        return protein;
    }
    public BigDecimal getCarbs() {
        return carbs;
    }
}
