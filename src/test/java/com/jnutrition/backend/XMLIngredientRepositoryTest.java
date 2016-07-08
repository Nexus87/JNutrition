package com.jnutrition.backend;



import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

import java.io.ByteArrayInputStream;

public class XMLIngredientRepositoryTest {
    private static final String appleXML =
            "<ingredient>\n" +
                "<name>Apple</name>\n" +
                "<kcal>3.0</kcal>\n" +
                "<protein>2.0</protein>\n" +
                "<carbs>1.0</carbs>\n" +
                "<fat>0.0</fat>\n" +
            "</ingredient>\n";
    private static final Ingredient appleIngredient = new Ingredient("Apple", 3, 2, 1, 0);

    private  static final String waterXML =
            "<ingredient>\n" +
                    "<name>Water</name>\n" +
                    "<kcal>1.0</kcal>\n" +
                    "<protein>1.0</protein>\n" +
                    "<carbs>1.0</carbs>\n" +
                    "<fat>0.0</fat>\n" +
            "</ingredient>\n";
    private static final Ingredient waterIngredient = new Ingredient("Water", 1, 1, 1, 0);

    private  static final String eggXML =
            "<ingredient>\n" +
                    "<name>Egg</name>\n" +
                    "<kcal>12.2</kcal>\n" +
                    "<protein>1.4</protein>\n" +
                    "<carbs>1.5</carbs>\n" +
                    "<fat>3.1</fat>\n" +
                    "</ingredient>\n";
    private static final Ingredient eggIngredient = new Ingredient("Egg", 12.2, 1.4, 1.5, 3.1);

    private static final String testFile =
            "<ingredients>\n" +
                appleXML +
            "</ingredients>";

    private static final String multipleTestData =
            "<ingredients>\n" +
                    appleXML +
                    waterXML +
            "</ingredients>";

    private static final String completeTestData =
            "<ingredients>\n" +
                    appleXML +
                    waterXML +
                    eggXML +
            "</ingredients>";

    @DataProvider
    public Object[][] singleIngredientData(){
        return new Object[][]{
                {testFile, appleIngredient}
        };
    }

    @Test(dataProvider = "singleIngredientData")
    public void parseString_ReturnContainingIngredient(String data, Ingredient expectedIngredient){
        XMLIngredientRepository repository = createIngredientRepository(data);

        assertThat(repository.getAllIngredients(), hasItem(expectedIngredient));
    }

    @DataProvider
    public Object[][] multipleIngredientData(){
        return new Object[][]{
                {multipleTestData, new Ingredient[]{appleIngredient, waterIngredient}}
        };
    }

    @Test(dataProvider = "multipleIngredientData")
    public void parseFileWithMultipleParameters(String data, Ingredient[] expectedIngredients){
        XMLIngredientRepository repository = createIngredientRepository(data);

        for (Ingredient expected : expectedIngredients) {
            assertThat(repository.getAllIngredients(), hasItem(expected));
        }
    }

    @DataProvider
    public Object[][] getIngredientByNameTestData(){
        return new Object[][]{
                {completeTestData, eggIngredient.getName(), eggIngredient},
                {completeTestData, appleIngredient.getName(), appleIngredient}
        };
    }

    @Test(dataProvider = "getIngredientByNameTestData")
    public void getIngredientByName(String data, String ingredientName, Ingredient expectedIngredient){
        XMLIngredientRepository repository = createIngredientRepository(data);

        Ingredient i = repository.getIngredientByName(ingredientName);

        assertEquals(i, expectedIngredient);
    }
    private XMLIngredientRepository createIngredientRepository(String data) {
        return new XMLIngredientRepository(new ByteArrayInputStream(data.getBytes()));
    }

}
