package com.jnutrition.backend;

import javafx.collections.ObservableList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

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

    private PlanModel createModel() {
        return new PlanModel();
    }
}