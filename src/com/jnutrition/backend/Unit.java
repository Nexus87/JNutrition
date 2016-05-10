package com.jnutrition.backend;

/**
 * Created by nexxuz0 on 22.04.2016.
 */
public class Unit {
    public static final Unit Gram = new Unit("g", "Gram", 1.0f);

    private final String abbreviation;
    private final String longName;
    private final float gram;

    public Unit(String abbreviation, String longName, float gram) {
        this.abbreviation = abbreviation;
        this.longName = longName;
        this.gram = gram;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getLongName() {
        return longName;
    }

    public float getGram() {
        return gram;
    }
}
