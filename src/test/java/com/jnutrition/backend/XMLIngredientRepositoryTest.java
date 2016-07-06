package com.jnutrition.backend;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.hamcrest.core.IsCollectionContaining.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

public class XMLIngredientRepositoryTest {
    private static final String testFile =
            "<ingredients>\n" +
                "<ingredient>\n" +
                    "<name>Apple</name>\n" +
                    "<kcal>3.0</kcal>\n" +
                    "<protein>2.0</protein>\n" +
                    "<carbs>1.0</carbs>\n" +
                    "<fat>0.0</fat>\n" +
                "</ingredient>\n" +
            "</ingredients>";

    @Test
    public void parseString_ReturnContainingIngredient() throws FileNotFoundException {
        Ingredient result = new Ingredient("Apple", 3.0, 2.0, 1.0, 0.0);
        XMLIngredientRepository repository = new XMLIngredientRepository(new ByteArrayInputStream(testFile.getBytes()));

        assertThat(repository.getAllIngredients(), hasItem(result));
    }
}
