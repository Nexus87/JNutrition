package com.jnutrition.view;

import com.jnutrition.backend.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.io.IOException;

import static com.jnutrition.view.Util.showUnitDialog;

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
        Pair<Double, Unit> result = showUnitDialog(i, unitRepository).orElse(null);

        if(result == null)
            return;

        model.addIngredient(result.getKey(), result.getValue(), i);

    }
}
