package com.jnutrition.model;

import javax.persistence.*;

@Entity
@Table(name = "PlanItems")
public class PlanItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ingredient", foreignKey = @ForeignKey(name = "PLANITEM_INGREDIENT_FK"))
    private Ingredient ingredient;
    @Column(name = "amount")
    private double amount;
    @ManyToOne
    @JoinColumn(name = "unit", foreignKey = @ForeignKey(name = "PLANITEM_UNIT_FK"))
    private Unit unit;

    public PlanItem() {
    }
    public PlanItem(Ingredient ingredient, double amount, Unit unit) {

        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;

    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
