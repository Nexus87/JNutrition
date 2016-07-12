package com.jnutrition.backend;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlanItemTest {
    @DataProvider
    public Object[][] ingredientTestData(){
        return new Object[][]{
                {new Ingredient("Apple", 1, 2, 3, 4)}
        };
    }

    @Test(dataProvider = "ingredientTestData")
    public void createPlanItem_WithDefaultUnit_DataEqualGivenData(Ingredient testIngredient){
        PlanItem item = new PlanItem(testIngredient, 100, Unit.GRAM);

        assertEquals(item.getKcal(), testIngredient.getKcal(), 1e-12);
        assertEquals(item.getProtein(), testIngredient.getProtein(), 1e-12);
        assertEquals(item.getCarbs(), testIngredient.getCarbs(), 1e-12);
        assertEquals(item.getFat(), testIngredient.getFat(), 1e-12);
    }

    @DataProvider
    public Object[][] amountAndUnitTestData(){
        return new Object[][]{
                {2, 4, 6, 8, new Ingredient("Apple", 1, 2, 3, 4), 200, Unit.GRAM},
                {2, 4, 6, 8, new Ingredient("Apple", 1, 2, 3, 4), 100, new Unit("Unit", 2)}
        };
    }

    @Test(dataProvider = "amountAndUnitTestData")
    public void creattePlan_WithDifferentUnitsAndAmounts_DataAsExpected(
            double expectedKcal, double expectedProtein, double expectedCarbs, double expectedFat,
            Ingredient testIngredient, double amount, Unit unit
    ){
        PlanItem item = new PlanItem(testIngredient, amount, unit);

        assertEquals(item.getKcal(), expectedKcal, 1e-12);
        assertEquals(item.getProtein(), expectedProtein, 1e-12);
        assertEquals(item.getCarbs(), expectedCarbs, 1e-12);
        assertEquals(item.getFat(), expectedFat, 1e-12);
    }

}
