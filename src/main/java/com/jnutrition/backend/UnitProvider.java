package com.jnutrition.backend;

import java.util.Collection;

/**
 * Created by nexxuz0 on 22.04.2016.
 */
public interface UnitProvider {

    default Unit getDefaultUnitForIngredient(String ingredientName) {
        return Unit.Gram;
    }

    Collection<Unit> getUnitsForIngredient(String ingredientName);
}
