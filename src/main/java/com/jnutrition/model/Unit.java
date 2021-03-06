package com.jnutrition.model;

import javax.persistence.*;

@Entity
@Table(name = "Units")
public class Unit {
    public static final Unit GRAM = new Unit("g", 1);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "unit_name")
    private String name;
    @Column(name = "factor")
    private double convertFactor;

    private Unit(){}
    public Unit(String name, double convertFactor) {
        this.name = name;
        this.convertFactor = convertFactor;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setConvertFactor(double convertFactor) {
        this.convertFactor = convertFactor;
    }

    public double inGram() {
        return convertFactor;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(convertFactor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Unit))
            return false;

        Unit unit = (Unit) o;

        if (Double.compare(unit.convertFactor, convertFactor) != 0)
            return false;
        return name.equals(unit.name);

    }

    @Override
    public String toString() {
        return name;
    }
}
