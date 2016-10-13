package com.jnutrition.view;

import com.jnutrition.backend.IngredientRepository;
import com.jnutrition.backend.PlanModel;
import com.jnutrition.backend.UnitRepository;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MainViewController extends FXMLController{

    @FXML
    private HBox ingredientView;
    @FXML
    private HBox planView;

    public MainViewController(){
        super();
        fxmlFilePath = "MainView.fxml";
        loadFXML();
    }

    public void setupController(IngredientRepository repository, UnitRepository unitRepository) {
        PlanModel planModel = new PlanModel();

        IngredientViewController ingredientViewController = new IngredientViewController();
        ingredientViewController.setupController(repository, unitRepository, planModel);
        ingredientView.getChildren().add(ingredientViewController.getView());
        PlanViewController planViewController = new PlanViewController();
        planViewController.setupController(planModel);
        planView.getChildren().add(planViewController.getView());
    }
}
