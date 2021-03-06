package com.jnutrition.model;

import com.jnutrition.repositories.PlanRepository;
import javafx.collections.ObservableList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class PlanModelTest {
    @DataProvider
    public Object[][] addIngredientTestData(){
        return new Object[][]{
                {new Ingredient("Apple", 1, 2, 3, 4), 100, new Unit("g", 1.0)}
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
        Unit defaultUnit = Unit.GRAM;

        for(Ingredient i : ingredients)
            model.addIngredient(defaultAmount, defaultUnit, i);

        assertEquals(totalKcal, model.getKcal().doubleValue(), 1e-12);
        assertEquals(totalProtein, model.getProtein().doubleValue(), 1e-12);
        assertEquals(totalCarbs, model.getCarbs().doubleValue(), 1e-12);
        assertEquals(totalFat, model.getFat().doubleValue(), 1e-12);
    }

    @DataProvider
    public Object[][] unitDataTestData(){
        return new Object[][]{
                { 1, 2, 3, 4, new Ingredient("Apple", 2, 4, 6, 8), new Unit("Unit", 50)}
        };
    }

    @Test(dataProvider = "unitDataTestData")
    public void addIngredient_WithDifferentUnitThanGramm_DataAreDifferentToDefaultUnit(
            double expectedKcal, double expectedProtein, double expectedCarbs, double expectedFat,
            Ingredient ingredient, Unit unit
    ) {
        PlanModel model = createModel();

        model.addIngredient(1, unit, ingredient);

        assertEquals(model.getKcal().doubleValue(), expectedKcal, 1e-12);
        assertEquals(model.getProtein().doubleValue(), expectedProtein, 1e-12);
        assertEquals(model.getCarbs().doubleValue(), expectedCarbs, 1e-12);
        assertEquals(model.getFat().doubleValue(), expectedFat, 1e-12);
    }

    @DataProvider
    public Object[][] removeItemTestData(){
        return new Object[][]{
                { 0, new Ingredient("Apple", 2, 4, 6, 8), new Unit("Unit", 50)}
        };
    }

    @Test(dataProvider = "removeItemTestData")
    public void removeIngredient_ItemIsInList_IngredientListDoesNotContainItem(int index, Ingredient ingredient, Unit unit){
        PlanModel model = createModel();
        model.addIngredient(1, unit, ingredient);

        model.removeItem(index);
        assertThat(model.getReadOnlyList(), not(hasItem(hasProperty("ingredient", is(ingredient)))));
    }

    @DataProvider
    public Object[][] removeItemMultipleTestData(){
        return new Object[][]{
                { 0, 1, 2, 3, 4, new Ingredient("Apple", 2, 4, 6, 8), new Ingredient("Orange", 1, 2, 3, 4), new Unit("Unit", 100)}
        };
    }

    @Test(dataProvider = "removeItemMultipleTestData")
    public void removeItem_ItemIsInList_DataAreAsExpected(
            int index,
            double expectedKcal, double expectedProtein, double expectedCarbs, double expectedFat,
            Ingredient ingredient, Ingredient ingredient2, Unit unit){

        PlanItem item = new PlanItem(ingredient, 1, unit);

        PlanModel model = createModel();
        model.addIngredient(1, unit, ingredient);
        model.addIngredient(1, unit, ingredient2);

        model.removeItem(index);

        assertEquals(model.getKcal().doubleValue(), expectedKcal, 1e-12);
        assertEquals(model.getProtein().doubleValue(), expectedProtein, 1e-12);
        assertEquals(model.getCarbs().doubleValue(), expectedCarbs, 1e-12);
        assertEquals(model.getFat().doubleValue(), expectedFat, 1e-12);
    }
    private PlanModel createModel() {
        PlanRepository planRepository = new PlanRepository() {
            @Override
            public Plan getPlanByName(String name) {
                return null;
            }

            @Override
            public void savePlan(Plan plan) {

            }
        };
        return new PlanModel(new Plan(), planRepository);
    }
}