package com.jnutrition.backend;


public class Unit {
    private String text;

    public Unit(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
