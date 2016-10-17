package com.jnutrition.DAO;

import com.jnutrition.model.Ingredient;

import java.util.List;

public interface IngredientDAO {
    void save(List<Ingredient> ingredientList);
    List<Ingredient> getAllIngredients();
    List<Ingredient> getIngredientsWihtNameLike(String filter);
}
