package com.jnutrition.backend;

import java.math.BigDecimal;

public class Ingredient {

    public Ingredient(String name, double kcal, double protein, double carbs, double fat){
        this.name = name;
        this.kcal = new BigDecimal(kcal);
        this.protein = new BigDecimal(protein);
        this.carbs = new BigDecimal(carbs);
        this.fat = new BigDecimal(fat);
    }

    public BigDecimal getFat() {
        return fat;
    }
    public String getName() {
        return name;
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

    private String name;
    private BigDecimal kcal;
    private BigDecimal protein;
    private BigDecimal carbs;
    private BigDecimal fat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;

        Ingredient that = (Ingredient) o;

        if (!name.equals(that.name)) return false;
        if (!kcal.equals(that.kcal)) return false;
        if (!protein.equals(that.protein)) return false;
        if (!carbs.equals(that.carbs)) return false;
        return fat.equals(that.fat);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + kcal.hashCode();
        result = 31 * result + protein.hashCode();
        result = 31 * result + carbs.hashCode();
        result = 31 * result + fat.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + kcal + ", " + protein + ", " + carbs + ", " + fat + "]";
    }

}
