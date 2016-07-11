package com.jnutrition.view;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientRepository;
import com.jnutrition.backend.PlanModel;
import com.jnutrition.backend.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public class MainViewController {
	@FXML
	private ListView<Ingredient> ingredientView;

	@FXML
    private TableView<Ingredient> planTable;

    @FXML
    private TableColumn<Ingredient, String> nameColumn;
    @FXML
    private TableColumn<Ingredient, Double> kcalColumn;
    @FXML
    private TableColumn<Ingredient, Double> proteinColumn;
    @FXML
    private TableColumn<Ingredient, Double> carbsColumn;
    @FXML
    private TableColumn<Ingredient, Double> fatColumn;
    @FXML
    private Label kcalLabel;
    @FXML
    private Label proteinLabel;
    @FXML
    private Label carbsLabel;
    @FXML
    private Label fatLabel;

	private final ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
    private IngredientRepository repository;
    private final PlanModel model = new PlanModel();

    public void initialize(){
		ingredientView.setItems(ingredientList);
        planTable.setItems(model.getReadOnlyList());

        ingredientView.setCellFactory(p -> new IngredientCell());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        fatColumn.setCellValueFactory(new PropertyValueFactory<>("fat"));

        kcalLabel.textProperty().bind(model.kcalProperty().asString());
        proteinLabel.textProperty().bind(model.proteinProperty().asString());
        carbsLabel.textProperty().bind(model.carbsProperty().asString());
        fatLabel.textProperty().bind(model.fatProperty().asString());


    }

	public void setupController(IngredientRepository repository){
        this.repository = repository;
		ingredientList.addAll(repository.getAllIngredients());

        ingredientView.setOnMouseClicked(e -> {
            if(e.getClickCount() != 2)
                return;

            listDoubleClickHandler();
        });
	}

    private void listDoubleClickHandler(){
        Ingredient i = ingredientView.getSelectionModel().getSelectedItem();

        Optional<Pair<Double, Unit>> result = showUnitDialog();
        model.addIngredient(i);

    }

    private Optional<Pair<Double, Unit>> showUnitDialog() {
        Dialog<Pair<Double, Unit>> unitDialog = new Dialog<>();
        unitDialog.setTitle("Chose amount and unit");

        unitDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();

        TextField amountField = new TextField();
        TextField unitField = new TextField();

        amountField.setId("amountField");
        unitField.setId("unitField");

        gridPane.add(new Label("Amount:"), 0, 0);
        gridPane.add(amountField, 1, 0);
        gridPane.add(new Label("Unit"), 0, 1);
        gridPane.add(unitField, 1, 1);

        unitDialog.getDialogPane().setContent(gridPane);

        unitDialog.setResultConverter( button ->{
            if(button == ButtonType.CANCEL)
                return null;
            return new Pair<Double, Unit>(Double.parseDouble(amountField.getText()), new Unit(unitField.getText()));
        });

        return unitDialog.showAndWait();
    }
}
