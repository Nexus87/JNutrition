package com.jnutrition.ui.ingredients;

import com.jnutrition.model.Ingredient;
import com.jnutrition.model.PlanModel;
import com.jnutrition.model.Unit;
import com.jnutrition.repositories.IngredientRepository;
import com.jnutrition.repositories.UnitRepository;
import com.jnutrition.ui.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static com.jnutrition.ui.Util.showUnitDialog;

@Component
public class IngredientViewController extends FXMLController implements Initializable {
    @FXML
    private ListView<Ingredient> ingredientView;
    @FXML
    private TextField filterBox;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private PlanModel model;
    @Autowired
    private IngredientRepository ingredientRepository;

    private void listDoubleClickHandler(){
        Ingredient i = ingredientView.getSelectionModel().getSelectedItem();
        if(i == null)
            return;
        Pair<Double, Unit> result = showUnitDialog(i, unitRepository).orElse(null);

        if(result == null)
            return;

        model.addIngredient(result.getKey(), result.getValue(), i);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filterBox.textProperty().addListener((observable, oldValue, newValue) -> {
            ingredientRepository.setNameFilter(newValue);
        });

        ingredientView.setItems(ingredientRepository.getAllIngredients());
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

    @Value("${fxml.jnutrition.ingredients.view}")
    @Override
    public void setFxmlFilePath(String filePath) {
        fxmlFilePath = filePath;
    }
}
