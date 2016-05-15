package com.jnutrition.databaseViews;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

public class ImageTableCell<S> extends TableCell<S, String> {
    private final IngredientImageProvider imageProvider;
    ImageView imageView = new ImageView();

    ImageTableCell(IngredientImageProvider imageProvider) {
        this.imageProvider = imageProvider;
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            imageView.setImage(null);
            setText(null);
            setGraphic(null);
            return;
        }

        imageView.setImage(imageProvider.getDefaultImage());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        setGraphic(imageView);
    }
}
