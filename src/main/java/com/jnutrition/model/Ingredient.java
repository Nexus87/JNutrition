package com.jnutrition.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Ingredients", uniqueConstraints = { @UniqueConstraint(columnNames = "name")})
public class Ingredient {

    private Ingredient(){}
    public Ingredient(String name, double kcal, double protein, double carbs, double fat){
        this(name,
                new BigDecimal(kcal),
                new BigDecimal(protein),
                new BigDecimal(carbs),
                new BigDecimal(fat));
    }

    public Ingredient(String name, BigDecimal kcal, BigDecimal protein, BigDecimal carbs, BigDecimal fat){
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        units.add(Unit.GRAM);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @Column(name = "fat")
    public BigDecimal getFat() {
        return fat;
    }
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }
    @Column(name = "kcal")
    public BigDecimal getKcal() {
        return kcal;
    }
    @Column(name = "protein")
    public BigDecimal getProtein() {
        return protein;
    }
    @Column(name = "carbs")
    public BigDecimal getCarbs() {
        return carbs;
    }
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Unit> units = new ArrayList<>();

    private String name;

    private void setName(String name) {
        this.name = name;
    }

    private void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    private void setProtein(BigDecimal protein) {
        this.protein = protein;
    }

    private void setCarbs(BigDecimal carbs) {
        this.carbs = carbs;
    }

    private void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    private BigDecimal kcal;
    private BigDecimal protein;
    private BigDecimal carbs;
    private BigDecimal fat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;

        Ingredient that = (Ingredient) o;

        if (name.compareTo(that.name) != 0) return false;
        if (kcal.compareTo(that.kcal) != 0) return false;
        if (protein.compareTo(that.protein) != 0) return false;
        if (carbs.compareTo(that.carbs) != 0) return false;
        return fat.compareTo(that.fat) == 0;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Unit> getUnits() {
        return units;
    }
}
