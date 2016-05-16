package com.jnutrition.backend;

import org.junit.Test;

import java.net.CookieHandler;
import java.util.Collection;

import static org.junit.Assert.*;


public abstract class IngredientProviderTest {
    protected abstract IngredientRepository getProvider();
    protected abstract void addIngredient(String name);

    @Test
    public void addIngredient_IngredientChangedHandlerIsCalled(){
        IngredientRepository provider = getProvider();
        final boolean[] wasCalled = {false};

        provider.addRepositoryChangeListener(() -> wasCalled[0] = true);
        addIngredient("name");

        assertTrue(wasCalled[0]);
    }

    @Test
    public void addIngredient_RegisterAndUnregister_HandlerWasNotCalled(){
        IngredientRepository provider = getProvider();
        final boolean[] wasCalled = {false};
        IngredientRepository.IngredientsChangedHandler handler = () -> wasCalled[0] = true;

        provider.addRepositoryChangeListener(handler);
        provider.removeRepositoryChangedHandler(handler);

        addIngredient("name");

        assertFalse(wasCalled[0]);
    }

    @Test
    public void addHandler_RegisterTwice_CalledTwice(){
        IngredientRepository provider = getProvider();
        final int[] callCount = {0};

        provider.addRepositoryChangeListener(() -> callCount[0]++);
        provider.addRepositoryChangeListener(() -> callCount[0]++);

        addIngredient("name");

        assertEquals(2, callCount[0]);
    }

    @Test
    public void searchIngredients_SearchEmptyString_ReturnsAllData(){
        IngredientRepository provider = getProvider();
        addIngredient("Name1");
        addIngredient("Name2");
        addIngredient("Name3");

        Collection<Ingredient> result = provider.searchIngredients("");

        assertTrue(containsIngredientWithName(result, "Name1"));
        assertTrue(containsIngredientWithName(result, "Name2"));
        assertTrue(containsIngredientWithName(result, "Name3"));

    }

    @Test
    public void searchIngredients_SearchExactName_ReturnsOneData(){
        IngredientRepository repository = getProvider();
        addIngredient("Name1");
        addIngredient("Name2");
        addIngredient("Name3");

        Collection<Ingredient> result = repository.searchIngredients("Name1");

        assertTrue(containsIngredientWithName(result, "Name1"));
        assertEquals(1, result.size());
    }

    @Test
    public void searchIngredients_SearchWithMultipleResults_ReturnsDataThatContainsString(){
        IngredientRepository repository = getProvider();
        addIngredient("Name1");
        addIngredient("Name12");
        addIngredient("Name3");

        Collection<Ingredient> result = repository.searchIngredients("Name1");

        assertTrue(containsIngredientWithName(result, "Name1"));
        assertTrue(containsIngredientWithName(result, "Name12"));
        assertEquals(2, result.size());
    }

    @Test
    public void searchIngredients_StringWithDifferentCasing_ReturnsCaseInsensitiveItems(){
        IngredientRepository repository = getProvider();
        addIngredient("Name1");
        addIngredient("Name2");
        addIngredient("Name3");

        Collection<Ingredient> result = repository.searchIngredients("nAmE1");

        assertTrue(containsIngredientWithName(result, "Name1"));
        assertEquals(1, result.size());
    }

    private boolean containsIngredientWithName(Collection<Ingredient> collection, String name){
        return collection.stream().filter(i -> i.getName().equals(name)).count() > 0;
    }
}
