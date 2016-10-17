package com.jnutrition.backend;

import com.jnutrition.DAO.IngredientDAO;
import com.jnutrition.model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HSQLIngredientRepository implements IngredientRepository{
    private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
    @Autowired
    private IngredientDAO ingredientDAO;

    @Override
    public ObservableList<Ingredient> getAllIngredients() {
        ingredients.clear();
        ingredients.addAll(ingredientDAO.getAllIngredients());

        return ingredients;
    }

    @Override
    public void setNameFilter(String name) {
        ingredients.clear();
        ingredients.addAll(ingredientDAO.getIngredientsWihtNameLike(name));
    }

    @Override
    public Object getIngredientByName(String name) {
        return new Object();
    }

    @Override
    public void close() {

    }

}
