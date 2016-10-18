package com.jnutrition.DAO.jpa;

import com.jnutrition.DAO.IngredientDAO;
import com.jnutrition.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JPAIngredientDAO implements IngredientDAO {
    @Autowired
    private DataBroker dataBroker;

    @Override
    public void save(List<Ingredient> ingredientList) {

    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return dataBroker.selectAll(Ingredient.class);
    }

    @Override
    public List<Ingredient> getIngredientsWihtNameLike(String filter) {
        return dataBroker.selectWherePropertyLike(Ingredient.class, "name", "%" + filter + "%");
    }
}
