package com.jnutrition.backend;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;


public abstract class IngredientRepositoryTest {
    protected abstract IngredientRepository getRepository();

    @Test
    public void addIngredient_IngredientChangedHandlerIsCalled(){
        IngredientRepository repository = getRepository();
        final boolean[] wasCalled = {false};

        repository.addRepositoryChangeListener(() -> wasCalled[0] = true);
        addIngredient(repository, "name");

        assertTrue(wasCalled[0]);
    }

    private void addIngredient(IngredientRepository repository, String name) {
        repository.insertIngredient(new Ingredient(name, 1.0, 1.0, 1.0, 1.0));
    }

    @Test
    public void addIngredient_RegisterAndUnregister_HandlerWasNotCalled(){
        IngredientRepository repository = getRepository();
        final boolean[] wasCalled = {false};
        IngredientRepository.IngredientsChangedHandler handler = () -> wasCalled[0] = true;

        repository.addRepositoryChangeListener(handler);
        repository.removeRepositoryChangedHandler(handler);

        addIngredient(repository, "name");

        assertFalse(wasCalled[0]);
    }

    @Test
    public void addHandler_RegisterTwice_CalledTwice(){
        IngredientRepository repository = getRepository();
        final int[] callCount = {0};

        repository.addRepositoryChangeListener(() -> callCount[0]++);
        repository.addRepositoryChangeListener(() -> callCount[0]++);

        addIngredient(repository, "name");

        assertEquals(2, callCount[0]);
    }

    @Test
    public void searchIngredients_SearchEmptyString_ReturnsAllData(){
        IngredientRepository repository = getRepository();
        addIngredient(repository, "Name1");
        addIngredient(repository, "Name2");
        addIngredient(repository, "Name3");

        Collection<Ingredient> result = repository.searchIngredients("");

        assertTrue(containsIngredientWithName(result, "Name1"));
        assertTrue(containsIngredientWithName(result, "Name2"));
        assertTrue(containsIngredientWithName(result, "Name3"));

    }

    @Test
    public void searchIngredients_SearchExactName_ReturnsOneData(){
        IngredientRepository repository = getRepository();
        addIngredient(repository, "Name1");
        addIngredient(repository, "Name2");
        addIngredient(repository, "Name3");

        Collection<Ingredient> result = repository.searchIngredients("Name1");

        assertTrue(containsIngredientWithName(result, "Name1"));
        assertEquals(1, result.size());
    }

    @Test
    public void searchIngredients_SearchWithMultipleResults_ReturnsDataThatContainsString(){
        IngredientRepository repository = getRepository();
        addIngredient(repository, "Name1");
        addIngredient(repository, "Name12");
        addIngredient(repository, "Name3");

        Collection<Ingredient> result = repository.searchIngredients("Name1");

        assertTrue(containsIngredientWithName(result, "Name1"));
        assertTrue(containsIngredientWithName(result, "Name12"));
        assertEquals(2, result.size());
    }

    @Test
    public void searchIngredients_StringWithDifferentCasing_ReturnsCaseInsensitiveItems(){
        IngredientRepository repository = getRepository();
        addIngredient(repository, "Name1");
        addIngredient(repository, "Name2");
        addIngredient(repository, "Name3");

        Collection<Ingredient> result = repository.searchIngredients("nAmE1");

        assertTrue(containsIngredientWithName(result, "Name1"));
        assertEquals(1, result.size());
    }

    @Test
    public void getIngredient_EmptyList_ReturnsNull(){
        IngredientRepository repository = getRepository();
        Ingredient returnValue = repository.getIngredient("Name");

        assertNull(returnValue);
    }

    @Test
    public void getIngredient_RequestExistingIngredient_ReturnsIngredient(){
        IngredientRepository repository = getRepository();
        Ingredient ingredient = new Ingredient("Name", 1, 1, 1, 1);
        repository.insertIngredient(ingredient);

        Ingredient returnValue = repository.getIngredient(ingredient.getName());

        assertEquals(ingredient, returnValue);
    }

    @Test
    public void getIngredient_RequestNonexistingIngredient_RetunrsNull(){
        IngredientRepository repository = getRepository();
        addIngredient(repository, "Name");
        addIngredient(repository, "Name2");

        Ingredient returnValue = repository.getIngredient("Name3");

        assertNull(returnValue);
    }

    @Test
    public void mergeIngredient_MergeNonExistingIngredient_NewIngredientAdded(){
        IngredientRepository repository = getRepository();
        Ingredient newIngredient = new Ingredient("Name2", 1, 2, 3, 4);
        addIngredient(repository, "Name");

        repository.mergeIngredient(newIngredient);

        assertEquals(newIngredient, repository.getIngredient(newIngredient.getName()));

    }

    @Test
    public void mergeIngredient_MergeExistingIngredient_ExistingIngredientIsOverriden(){
        IngredientRepository repository = getRepository();
        Ingredient oldIngredient = new Ingredient("Name", 1, 2, 3, 4);
        Ingredient newIngredient = new Ingredient("Name", 5, 6, 7, 8);
        repository.insertIngredient(oldIngredient);

        repository.mergeIngredient(newIngredient);

        assertEquals(newIngredient, repository.getIngredient(newIngredient.getName()));
    }
    private boolean containsIngredientWithName(Collection<Ingredient> collection, String name){
        return collection.stream().filter(i -> i.getName().equals(name)).count() > 0;
    }
}
