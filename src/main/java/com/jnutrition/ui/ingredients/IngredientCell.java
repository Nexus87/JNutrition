package com.jnutrition.ui.ingredients;

import com.jnutrition.backend.Ingredient;
import javafx.scene.control.ListCell;

class IngredientCell extends ListCell<Ingredient>{
    @Override
    protected void updateItem(Ingredient item, boolean empty) {
        super.updateItem(item, empty);

        if(item == null || empty){
            setText("");
            setGraphic(null);
            return;
        }

        setText(item.getName());
        setGraphic(null);
    }
}
