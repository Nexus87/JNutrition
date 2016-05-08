package com.jnutrition.databaseViews;

import javafx.scene.image.ImageView;

public class DefaultIngredientImageProvider implements IngredientImageProvider{
    @Override
    public ImageView getDefaultImage() {
        return new ImageView();
    }
}
