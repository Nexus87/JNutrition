package com.jnutrition.backend;

import org.junit.Before;

public class InMemoryIngredientStoreTest extends IngredientProviderTest {
    InMemoryIngredientStore database;

    @Before
    public void setUp(){
        database = new InMemoryIngredientStore();
    }
    @Override
    public IngredientRepository getProvider() {
        return database;
    }

    @Override
    public void addIngredient(String name) {
        database.addIngredient(new Ingredient(name, 1.0d, 2.0d, 3.0d, 4.0d));
    }


}
