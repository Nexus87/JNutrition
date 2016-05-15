package com.jnutrition.backend;

import org.junit.Before;

public class InMemoryIngredientStoreTest extends IngredientProviderTest {
    InMemoryIngredientStore database;

    @Before
    public void setUp(){
        database = new InMemoryIngredientStore();
    }
    @Override
    public IngredientProvider getProvider() {
        return database;
    }

    @Override
    public void addIngredient() {
        database.addIngredient(new Ingredient("Name", 1.0d, 2.0d, 3.0d, 4.0d));
    }


}
