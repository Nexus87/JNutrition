package com.jnutrition.databaseViews;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ImageTableCell<S> extends TableCell<S, String> {
    private final IngredientImageProvider imageProvider;
    private final Label nameLabel = new Label();
    private final HBox layout = new HBox();
    private final ImageView imageView = new ImageView();

    ImageTableCell(IngredientImageProvider imageProvider) {
        this.imageProvider = imageProvider;
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        layout.getChildren().addAll(imageView, nameLabel);
        layout.setAlignment(Pos.CENTER);
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
        nameLabel.setText(item);

        setGraphic(layout);
    }
}
