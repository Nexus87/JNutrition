package com.jnutrition.backend;

import com.jnutrition.DAO.UnitDAO;
import com.jnutrition.model.Ingredient;
import com.jnutrition.model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JPAUnitRepository implements UnitRepository{
    @Autowired
    private UnitDAO unitDAO;

    @Override
    public ObservableList<Unit> getUnitForIngredient(Ingredient ingredient) {
        ObservableList<Unit> units = FXCollections.observableList(unitDAO.getUnitsForIngredient(ingredient));
        units.add(Unit.GRAM);
        return units;
    }

    @Override
    public ObservableList<Unit> getUnitForIngredient(String ingredient) {
        return null;
    }
}
