package com.jnutrition.view;


import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.Unit;
import com.jnutrition.backend.UnitRepository;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public final class Util {
    private Util(){}

    public static Optional<Pair<Double, Unit>> showUnitDialog(Ingredient ingredient, UnitRepository unitRepository) {
        Dialog<Pair<Double, Unit>> unitDialog = new Dialog<>();
        unitDialog.setTitle("Chose amount and unit");

        unitDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();

        TextField amountField = new TextField();
        ComboBox<Unit> unitField = new ComboBox<>();

        amountField.setId("amountField");
        unitField.setId("unitField");
        unitField.setItems(unitRepository.getUnitForIngredient(ingredient));

        unitField.setValue(Unit.GRAM);
        gridPane.add(new Label("Amount:"), 0, 0);
        gridPane.add(amountField, 1, 0);
        gridPane.add(unitField, 2, 0);

        unitDialog.getDialogPane().setContent(gridPane);

        unitDialog.setResultConverter( button ->{
            if(button == ButtonType.CANCEL)
                return null;
            return new Pair<>(Double.parseDouble(amountField.getText()), unitField.getValue());
        });

        return unitDialog.showAndWait();
    }
}
