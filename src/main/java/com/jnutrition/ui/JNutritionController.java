package com.jnutrition.ui;

import com.jnutrition.ui.ingredients.IngredientViewController;
import com.jnutrition.ui.plan.PlanViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class JNutritionController extends FXMLController implements Initializable{

    @FXML
    private HBox ingredientView;
    @FXML
    private HBox planView;
    @Autowired
    private IngredientViewController ingredientViewController;
    @Autowired
    private PlanViewController planViewController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ingredientView.getChildren().add(ingredientViewController.getView());
        planView.getChildren().add(planViewController.getView());
    }

    @Value("${fxml.jnutrition.jnutrition}")
    @Override
    public void setFxmlFilePath(String filePath) {
        fxmlFilePath = filePath;
    }
}
