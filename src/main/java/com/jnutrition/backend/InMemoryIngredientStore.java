package com.jnutrition.backend;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryIngredientStore implements IngredientProvider{

    private final ArrayList<Ingredient> ingredients = new ArrayList<>();
    private final ArrayList<IngredientsChangedHandler> eventHandler = new ArrayList<>();

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
    public void addIngredientsChangedHandler(IngredientsChangedHandler handler) {
        eventHandler.add(handler);
    }

    @Override
    public void removeIngredientsChangedHandler(IngredientsChangedHandler handler) {
        eventHandler.remove(handler);
    }


    public void addIngredient(Ingredient ingredient) throws IllegalArgumentException {
        if(containsIngredient(ingredient))
            throw new IllegalArgumentException("Database already contains an ingredient with name " + ingredient.getName());

        ingredients.add(ingredient);
        onIngredientsChanged();
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
