package com.jnutrition.backend;

import java.util.Collection;

public class DummyIngredientDatabase implements IngredientProvider, IngredientStore{
    @Override
    public Collection<Ingredient> getAllIngredients() {
        return null;
    }

    @Override
    public Ingredient getIngredient(String name) {
        return null;
    }

    @Override
    public Collection<Ingredient> searchIngredients(String searchString) {
        return null;
    }

    @Override
    public void addIngredient(Ingredient ingredient) throws IllegalArgumentException {

    }

    @Override
    public boolean containsIngredient(Ingredient ingredient) {
        return false;
    }

    @Override
    public boolean containsIngredient(String name) {
        return false;
    }

    @Override
    public void removeIngredient(String name) throws IllegalArgumentException {

    }

    @Override
    public void mergeIngredient(Ingredient ingredient) {

    }
}
