package com.jnutrition.view;

import com.jnutrition.backend.IngredientRepository;
import com.jnutrition.backend.PlanModel;
import com.jnutrition.backend.UnitRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainViewController extends AnchorPane{

    @FXML
    private IngredientViewController ingredientViewController;
    @FXML
    private PlanViewController planViewController;

    public MainViewController(){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setupController(IngredientRepository repository, UnitRepository unitRepository) {
        PlanModel planModel = new PlanModel();
        ingredientViewController.setupController(repository, unitRepository, planModel);
        planViewController.setupController(unitRepository, planModel);
    }
}
