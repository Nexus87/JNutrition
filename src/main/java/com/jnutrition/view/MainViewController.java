package com.jnutrition.view;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientRepository;
import com.jnutrition.backend.PlanModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

        model.addIngredient(i);

    }
}
