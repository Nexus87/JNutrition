package com.jnutrition.view;

import com.jnutrition.backend.IngredientRepository;
import com.jnutrition.backend.PlanModel;
import com.jnutrition.backend.UnitRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends AnchorPane implements Initializable{

    @FXML
    private VBox ingredientView;
    @FXML
    private VBox planView;

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

        IngredientViewController ingredientViewController = new IngredientViewController();
        ingredientViewController.setupController(repository, unitRepository, planModel);
        ingredientView.getChildren().add(ingredientViewController.getView());
        PlanViewController planViewController = new PlanViewController();
        planViewController.setupController(planModel);
        planView.getChildren().add(planViewController.getView());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
