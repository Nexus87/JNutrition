package com.jnutrition.backend;

import org.junit.Assert;
import org.junit.Test;


public abstract class IngredientProviderTest {
    protected abstract IngredientProvider getProvider();
    protected abstract void addIngredient();

    @Test
    public void addIngredient_IngredientChangedHandlerIsCalled(){
        IngredientProvider provider = getProvider();
        final boolean[] wasCalled = {false};

        provider.addIngredientsChangedHandler(() -> wasCalled[0] = true);
        addIngredient();

        Assert.assertTrue(wasCalled[0]);
    }

    @Test
    public void addIngredient_RegisterAndUnregister_HandlerWasNotCalled(){
        IngredientProvider provider = getProvider();
        final boolean[] wasCalled = {false};
        IngredientProvider.IngredientsChangedHandler handler = () -> wasCalled[0] = true;

        provider.addIngredientsChangedHandler(handler);
        provider.removeIngredientsChangedHandler(handler);

        addIngredient();

        Assert.assertFalse(wasCalled[0]);
    }

    @Test
    public void addHandler_RegisterTwice_CalledTwice(){
        IngredientProvider provider = getProvider();
        final int[] callCount = {0};

        provider.addIngredientsChangedHandler(() -> callCount[0]++);
        provider.addIngredientsChangedHandler(() -> callCount[0]++);

        addIngredient();

        Assert.assertEquals(2, callCount[0]);
    }
}
