package com.jnutrition.backend;


public class Unit {
    public static final Unit GRAM = new Unit("g", 1);

    private final String name;
    private double convertFactor;

    public Unit(String name, double convertFactor) {
        this.name = name;
        this.convertFactor = convertFactor;
    }

    @Override
    public String toString() {
        return name;
    }

    public double inGram() {
        return convertFactor;
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
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(convertFactor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
