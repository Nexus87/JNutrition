package com.jnutrition.backend;

import org.junit.Before;

public class InMemoryIngredientRepositoryTest extends IngredientRepositoryTest {
    InMemoryIngredientRepository repository;

    @Before
    public void setUp(){
        repository = new InMemoryIngredientRepository();
    }
    @Override
    public IngredientRepository getRepository() {
        return repository;
    }

}
