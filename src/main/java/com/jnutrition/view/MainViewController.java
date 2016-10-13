package com.jnutrition.view;

import com.jnutrition.backend.IngredientRepository;
import com.jnutrition.backend.UnitRepository;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainViewController extends FXMLController{

    @FXML
    private HBox ingredientView;
    @FXML
    private HBox planView;
    @Autowired
    private IngredientViewController ingredientViewController;
    @Autowired
    private PlanViewController planViewController;

    public MainViewController(){
        super();
        fxmlFilePath = "MainView.fxml";
    }

    public void setupController(IngredientRepository repository, UnitRepository unitRepository) {
        ingredientView.getChildren().add(ingredientViewController.getView());
        planView.getChildren().add(planViewController.getView());
    }
}
