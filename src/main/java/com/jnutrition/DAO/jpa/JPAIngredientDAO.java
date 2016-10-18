package com.jnutrition.DAO.jpa;

import com.jnutrition.DAO.IngredientDAO;
import com.jnutrition.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class JPAIngredientDAO implements IngredientDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataBrocker dataBrocker;

    @Override
    public void save(List<Ingredient> ingredientList) {

    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return dataBrocker.selectAll(Ingredient.class);
    }

    @Override
    public List<Ingredient> getIngredientsWihtNameLike(String filter) {
        return dataBrocker.selectWherePropertyLike(Ingredient.class, "name", "%" + filter + "%");
    }
}
