package com.jnutrition.backend;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryIngredientDatabase implements IngredientProvider, IngredientStore{

    private final ArrayList<Ingredient> ingredients = new ArrayList<>();

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return ingredients;
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
        if(containsIngredient(ingredient.getName()))
            throw new IllegalArgumentException("Database already contains an ingredient with name " + ingredient.getName());
        ingredients.add(ingredient);
    }

    @Override
    public boolean containsIngredient(Ingredient ingredient) {
        return false;
    }

    @Override
    public boolean containsIngredient(String name) {
        return ingredients.stream().anyMatch(i -> i.getName().equals(name));
    }

    @Override
    public void removeIngredient(String name) throws IllegalArgumentException {

    }

    @Override
    public void mergeIngredient(Ingredient ingredient) {

    }
}
