package com.jnutrition.backend;

import javafx.collections.ObservableList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

public class PlanModelTest {
    @DataProvider
    public Object[][] addIngredientTestData(){
        return new Object[][]{
                {new Ingredient("Apple", 1, 2, 3, 4)}
        };
    }

    @Test(dataProvider = "addIngredientTestData")
    public void addIngredient_ObservableListIstUpdated(Ingredient testIngredient){
        PlanModel model = createModel();

        model.addIngredient(testIngredient);
        ObservableList<Ingredient> list = model.getReadOnlyList();

        assertThat(list, hasItem(testIngredient));
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

        for(Ingredient i : ingredients)
            model.addIngredient(i);

        assertEquals(totalKcal, model.getKcal(), 1e-12);
        assertEquals(totalProtein, model.getProtein(), 1e-12);
        assertEquals(totalCarbs, model.getCarbs(), 1e-12);
        assertEquals(totalFat, model.getFat(), 1e-12);
    }

    private PlanModel createModel() {
        return new PlanModel();
    }
}