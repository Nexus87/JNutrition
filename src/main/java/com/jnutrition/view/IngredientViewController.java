package com.jnutrition.view;

import com.jnutrition.backend.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

public class IngredientViewController extends AnchorPane {
    @FXML
    private ListView<Ingredient> ingredientView;
    @FXML
    private TextField filterBox;

    private UnitRepository unitRepository;
    private PlanModel model;

    public IngredientViewController(){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IngredientView.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void initialize(){
        ingredientView.setCellFactory(p -> {
            IngredientCell cell = new IngredientCell();
            cell.setOnMouseClicked(event ->{
                if(event.getClickCount() != 2)
                    return;
                listDoubleClickHandler();
            });
            return cell;
        });
    }

    public void setupController(IngredientRepository repository, UnitRepository unitRepository, PlanModel model){
        this.unitRepository = unitRepository;
        this.model = model;

        filterBox.textProperty().addListener((observable, oldValue, newValue) -> {
            repository.setNameFilter(newValue);
        });

        ingredientView.setItems(repository.getAllIngredients());
    }

    private void listDoubleClickHandler(){
        Ingredient i = ingredientView.getSelectionModel().getSelectedItem();
        if(i == null)
            return;
        Pair<Double, Unit> result = showUnitDialog(i).orElse(null);

        if(result == null)
            return;

        model.addIngredient(result.getKey(), result.getValue(), i);

    }

    private Optional<Pair<Double, Unit>> showUnitDialog(Ingredient ingredient) {
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
