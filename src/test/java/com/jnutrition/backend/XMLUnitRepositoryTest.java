package com.jnutrition.backend;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class XMLUnitRepositoryTest {
    private static final String unitData =
            "<Units>" +
                "<Unit>" +
                    "<Name>Unit</Name>" +
                    "<Gram>50</Gram>" +
                    "<Ingredient>Apple</Ingredient>" +
                "</Unit>" +
                "<Unit>" +
                    "<Name>Unit</Name>" +
                    "<Gram>50</Gram>" +
                    "<Ingredient>Water</Ingredient>" +
                "</Unit>" +
                "<Unit>" +
                    "<Name>Unit</Name>" +
                    "<Gram>50</Gram>" +
                    "<Ingredient>Egg</Ingredient>" +
                "</Unit>" +
                "<Unit>" +
                    "<Name>Liter</Name>" +
                    "<Gram>100</Gram>" +
                    "<Ingredient>Water</Ingredient>" +
                "</Unit>" +
            "</Units>";

    private static final Unit appleUnit = new Unit("Unit", 50);
    private static final Unit waterUnit = new Unit("Unit", 50);
    private static final Unit waterLiter = new Unit("Liter", 100);
    private static final Unit eggUnit = new Unit("Unit", 50);

    @Test
    public void getUnits_ListAlwaysContainsGram(){
        XMLUnitRepository repository = createRepository();

        Collection<Unit> returnedUnits = repository.getUnitForIngredient("Coffee");

        assertThat(returnedUnits, hasItem(Unit.GRAM));
    }

    @Test
    public void getUnits_OneUnitForKnownType(){
        XMLUnitRepository repository = createRepository();

        Collection<Unit> returnedUnits = repository.getUnitForIngredient("Apple");

        assertThat(returnedUnits, hasItem(appleUnit));
    }

    @Test
    public void getUnits_IngredientWithMultipleUnits_CollectionContainsAll(){
        XMLUnitRepository repository = createRepository();

        Collection<Unit> returnedUnits = repository.getUnitForIngredient("Water");

        assertThat(returnedUnits, hasItem(waterUnit));
        assertThat(returnedUnits, hasItem(waterLiter));
    }

    private XMLUnitRepository createRepository() {
        return new XMLUnitRepository(new ByteArrayInputStream(unitData.getBytes()));
    }
}
