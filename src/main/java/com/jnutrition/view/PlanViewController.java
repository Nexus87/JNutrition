package com.jnutrition.view;

import com.jnutrition.backend.PlanItem;
import com.jnutrition.backend.PlanModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PlanViewController extends FXMLController implements Initializable{
    public PlanViewController(){
        super();
        fxmlFilePath = "PlanView.fxml";
    }

    @Autowired
    private PlanModel model;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kcalLabel.textProperty().bind(model.kcalProperty().asString());
        proteinLabel.textProperty().bind(model.proteinProperty().asString());
        carbsLabel.textProperty().bind(model.carbsProperty().asString());
        fatLabel.textProperty().bind(model.fatProperty().asString());

        planList.setCellFactory(param -> new PlanListCell(model));
        planList.setItems(model.getReadOnlyList());
    }
}
