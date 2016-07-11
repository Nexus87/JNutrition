package com.jnutrition.backend;

import javafx.collections.ObservableList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.testng.Assert.assertEquals;

public class PlanModelTest {
    @DataProvider
    public Object[][] addIngredientTestData(){
        return new Object[][]{
                {new Ingredient("Apple", 1, 2, 3, 4), 100, new Unit("g")}
        };
    }

    @Test(dataProvider = "addIngredientTestData")
    public void addIngredient_ObservableListIstUpdated(Ingredient testIngredient, double amount, Unit unit){
        PlanModel model = createModel();

        model.addIngredient(amount, unit, testIngredient);
        ObservableList<PlanItem> list = model.getReadOnlyList();

        assertThat(list, hasItem(hasProperty("ingredient", is(testIngredient))));
    }

    @DataProvider
    public Object[][] totalNutritionalDataTestData(){
        return new Object[][]{
                { 1, 2, 3, 4, new Ingredient[]{ new Ingredient("Apple", 1, 2, 3, 4) }},
                { 2, 4, 6, 8, new Ingredient[]{ new Ingredient("Apple", 1, 2, 3, 4), new Ingredient("Egg", 1, 2, 3, 4)}}
        };
    }

    @Test(dataProvider = "totalNutritionalDataTestData")
    public void addIngredient_CheckTotalData(
            double totalKcal, double totalProtein, double totalCarbs, double totalFat,
            Ingredient[] ingredients)
    {
        PlanModel model = createModel();
        double defaultAmount = 100;
        Unit defaultUnit = new Unit("g");

        for(Ingredient i : ingredients)
            model.addIngredient(defaultAmount, defaultUnit, i);

        assertEquals(totalKcal, model.getKcal(), 1e-12);
        assertEquals(totalProtein, model.getProtein(), 1e-12);
        assertEquals(totalCarbs, model.getCarbs(), 1e-12);
        assertEquals(totalFat, model.getFat(), 1e-12);
    }

    private PlanModel createModel() {
        return new PlanModel();
    }
}