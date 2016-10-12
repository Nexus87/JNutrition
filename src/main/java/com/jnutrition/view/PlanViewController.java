package com.jnutrition.view;

import com.jnutrition.backend.PlanItem;
import com.jnutrition.backend.PlanModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlanViewController extends FXMLController{
    public PlanViewController(){
        super();
        fxmlFilePath = "PlanView.fxml";
        loadFXML();
    }

    @FXML
    private ListView<PlanItem> planList;
    @FXML
    private Label kcalLabel;
    @FXML
    private Label proteinLabel;
    @FXML
    private Label carbsLabel;
    @FXML
    private Label fatLabel;

    public void setupController(PlanModel model){
        kcalLabel.textProperty().bind(model.kcalProperty().asString());
        proteinLabel.textProperty().bind(model.proteinProperty().asString());
        carbsLabel.textProperty().bind(model.carbsProperty().asString());
        fatLabel.textProperty().bind(model.fatProperty().asString());

        planList.setCellFactory(param -> new PlanListCell(model));
        planList.setItems(model.getReadOnlyList());
    }


}
