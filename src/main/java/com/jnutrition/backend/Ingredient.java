package com.jnutrition.backend;

import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "kcal", "protein", "carbs", "fat"})
@XmlRootElement
public class Ingredient {

    public Ingredient(){}
    public Ingredient(String name, double kcal, double protein, double carbs, double fat) {
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public double getFat() {
        return fat;
    }
    @XmlElement
    public void setFat(double fat) {
        this.fat = fat;
    }
    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    public double getKcal() {
        return kcal;
    }
    @XmlElement
    public void setKcal(double kcal) {
        this.kcal = kcal;
    }
    public double getProtein() {
        return protein;
    }
    @XmlElement
    public void setProtein(double protein) {
        this.protein = protein;
    }
    public double getCarbs() {
        return carbs;
    }
    @XmlElement
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    private String name;
    private double kcal;
    private double protein;
    private double carbs;
    private double fat;


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
        return name.equals(other.name) &&
                Math.abs(kcal - other.kcal) < DELTA &&
                Math.abs(protein - other.protein) < DELTA &&
                Math.abs(carbs - other.carbs) < DELTA &&
                Math.abs(fat - other.fat) < DELTA;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + kcal + ", " + protein + ", " + carbs + ", " + fat + "]";
    }

}
