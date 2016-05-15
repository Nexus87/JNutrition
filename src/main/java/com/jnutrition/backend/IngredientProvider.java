package com.jnutrition.backend;

import java.util.Collection;

public interface IngredientProvider {
    interface IngredientsChangedHandler{
        void ingredientsChanged();
    }

    /**
     * Returns all stored ingredients
     * @return
     */
    Collection<Ingredient> getAllIngredients();

    /**
     * Returns the ingredient with the given name or null
     * @param name
     * @return Ingredient or null if no Ingredient is found
     */
    Ingredient getIngredient(String name);

    /**
     * Returns all ingredients that contain the search string
     * @param searchString
     * @return A collection with the found ingredients
     */
    Collection<Ingredient> searchIngredients(String searchString);

    void addIngredientsChangedHandler(IngredientsChangedHandler handler);

    void removeIngredientsChangedHandler(IngredientsChangedHandler handler);
}
