package com.jnutrition.databaseViews;

import javafx.scene.image.Image;

public class DefaultIngredientImageProvider implements IngredientImageProvider{
    private final Image defaultImage;

    public  DefaultIngredientImageProvider(){
        defaultImage = new Image(getClass().getResourceAsStream("/defaultImage.png"));
    }
    @Override
    public Image getDefaultImage() {
        return defaultImage;
    }
}
