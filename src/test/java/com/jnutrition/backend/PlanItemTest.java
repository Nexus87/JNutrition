package com.jnutrition.backend;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

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

        assertEquals(item.getKcal(), testIngredient.getKcal().setScale(2, BigDecimal.ROUND_HALF_UP));
        assertEquals(item.getProtein(), testIngredient.getProtein().setScale(2, BigDecimal.ROUND_HALF_UP));
        assertEquals(item.getCarbs(), testIngredient.getCarbs().setScale(2, BigDecimal.ROUND_HALF_UP));
        assertEquals(item.getFat(), testIngredient.getFat().setScale(2, BigDecimal.ROUND_HALF_UP));
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

        assertEquals(item.getKcal().doubleValue(), expectedKcal, 1e-12);
        assertEquals(item.getProtein().doubleValue(), expectedProtein, 1e-12);
        assertEquals(item.getCarbs().doubleValue(), expectedCarbs, 1e-12);
        assertEquals(item.getFat().doubleValue(), expectedFat, 1e-12);
    }

}
