package com.jnutrition.backend;

/**
 * Created by nexxuz01 on 22.04.2016.
 */
public interface IngredientStore {

    /**
     * Adds a single ingredient.
     * @param ingredient
     * @throws IllegalArgumentException If another ingredient with this name is stored
     */
    void addIngredient(Ingredient ingredient) throws IllegalArgumentException;
    /**
     * Returns if an ingredient that matches all fields is stored
     * @param ingredient
     * @return true if a match is found
     */
    boolean containsIngredient(Ingredient ingredient);
    /**
     * Searches the storage if it contains an ingredient with the given name
     * @param name
     * @return true if a match is found
     */
    boolean containsIngredient(String name);
    /**
     * Removes the ingredient with the given name
     * @param name
     * @throws IllegalArgumentException If no ingredient with the given name is stored.
     */
    void removeIngredient(String name) throws IllegalArgumentException;

    /**
     * Adds the ingredient. If already an ingredient with the given name is stored, it will
     * update the data.
     * @param ingredient
     */
    void mergeIngredient(Ingredient ingredient);
}
