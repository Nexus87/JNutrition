package com.jnutrition.backend;

import java.util.Collection;

public interface IngredientRepository {

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

    /**
     * Inserts an ingredient into the repository.
     * Will do nothing, if the repository already contains an ingredient with this name
     * @param ingredient    Ingredient that should be insert.
     */
    void insertIngredient(Ingredient ingredient);

    /**
     * Inserts an ingredient or overrides an existing one.
     * @param ingredient    Ingredient to be merged
     */
    void mergeIngredient(Ingredient ingredient);

    /**
     *
     * @param listener
     */
    void addRepositoryChangeListener(IngredientsChangedHandler listener);

    void removeRepositoryChangedHandler(IngredientsChangedHandler listener);
}
