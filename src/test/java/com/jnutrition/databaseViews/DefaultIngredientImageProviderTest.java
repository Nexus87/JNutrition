package com.jnutrition.databaseViews;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultIngredientImageProviderTest {
    DefaultIngredientImageProvider provider;

    @Before
    public void setUp(){
        provider = new DefaultIngredientImageProvider();
    }

    @Test
    public void getDefaultImage_ImageNotNull(){
        Image defaultImage = provider.getDefaultImage();

        assertNotNull(defaultImage);
    }
}
