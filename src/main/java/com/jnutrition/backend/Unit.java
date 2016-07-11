package com.jnutrition.backend;


public class Unit {
    private final String text;
    private double convertFactor;

    public Unit(String text, double convertFactor) {
        this.text = text;
        this.convertFactor = convertFactor;
    }

    @Override
    public String toString() {
        return text;
    }

    public double inGram() {
        return convertFactor;
    }
}
