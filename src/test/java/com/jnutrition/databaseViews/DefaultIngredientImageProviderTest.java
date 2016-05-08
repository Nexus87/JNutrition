package com.jnutrition.databaseViews;


import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultIngredientImageProviderTest {
    DefaultIngredientImageProvider provider;

    @Before
    public void setUp(){
        provider = new DefaultIngredientImageProvider();
    }

    @Test
    public void getDefaultImage_ImageNotNull(){
        ImageView defaultImage = provider.getDefaultImage();

        Assert.assertNotNull(defaultImage);
    }
}
