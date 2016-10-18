package com.jnutrition.DAO.jpa;

import com.jnutrition.DAO.UnitDAO;
import com.jnutrition.model.Ingredient;
import com.jnutrition.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class JPAUnitDAO implements UnitDAO{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public void save(List<Unit> units) {

    }

    @Override
    public List<Unit> getUnitsForIngredient(Ingredient ingredient) {
        return ingredient.getUnits();
    }
}
