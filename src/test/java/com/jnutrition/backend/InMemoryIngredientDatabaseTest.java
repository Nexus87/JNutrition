package com.jnutrition.backend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class InMemoryIngredientDatabaseTest {
    InMemoryIngredientDatabase database;

    @Before
    public void setUp(){
        database = new InMemoryIngredientDatabase();
    }

    @Test
    public void addIngredient_IngredientsAreStored(){
        Ingredient testData = new Ingredient("Name", 1.0d, 2.0d, 3.0d, 4.0d);

        database.addIngredient(testData);

        Collection<Ingredient> result = database.getAllIngredients();

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(testData, result.stream().findFirst().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addIngredient_SameItemTwice_ThrowsException(){
        Ingredient testData = new Ingredient("Name", 1.0d, 2.0d, 3.0d, 4.0d);

        database.addIngredient(testData);
        database.addIngredient(testData);
    }

    @Test(expected =  IllegalArgumentException.class)
    public void addIngredient_TwoItemsWithSameName_ThrowsException(){
        Ingredient testData = new Ingredient("Name", 1.0d, 2.0d, 3.0d, 4.0d);
        Ingredient testData2 = new Ingredient("Name", 5.0d, 6.0d, 7.0d, 8.0d);

        database.addIngredient(testData);
        database.addIngredient(testData2);
    }
}
