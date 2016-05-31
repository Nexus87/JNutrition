package com.jnutrition.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.jar.Attributes;
import java.util.stream.Collectors;

public class InMemoryIngredientRepository implements IngredientRepository {

    private final ArrayList<Ingredient> ingredients = new ArrayList<>();
    private final ArrayList<IngredientsChangedHandler> eventHandler = new ArrayList<>();

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return ingredients;
    }

    @Override
    public Ingredient getIngredient(String name) {
        return ingredients.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Ingredient> searchIngredients(String searchString) {
        return ingredients.stream()
                .filter(i -> i.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void addRepositoryChangeListener(IngredientsChangedHandler listener) {
        eventHandler.add(listener);
    }

    @Override
    public void removeRepositoryChangedHandler(IngredientsChangedHandler listener) {
        eventHandler.remove(listener);
    }

    @Override
    public void insertIngredient(Ingredient ingredient) throws IllegalArgumentException {
        if(containsIngredient(ingredient))
            throw new IllegalArgumentException("Database already contains an ingredient with name " + ingredient.getName());

        ingredients.add(ingredient);
        onIngredientsChanged();
    }

    @Override
    public void mergeIngredient(Ingredient ingredient) {
        ingredients.removeIf(i -> i.getName().equals(ingredient.getName()));
        ingredients.add(ingredient);
    }

    public boolean containsIngredient(Ingredient ingredient) {
        return containsIngredient(ingredient.getName());
    }

    private void onIngredientsChanged() {
        eventHandler.forEach(IngredientsChangedHandler::ingredientsChanged);
    }

    public boolean containsIngredient(String name) {
        return ingredients.stream().anyMatch(i -> i.getName().equals(name));
    }
}
