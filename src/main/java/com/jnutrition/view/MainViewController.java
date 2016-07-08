package com.jnutrition.view;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientRepository;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class MainViewController {
	@FXML
	private ListView<String> ingredientView;

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

	private final ObservableList<String> ingredientList = FXCollections.observableArrayList();
    private final ObservableList<Ingredient> planList = FXCollections.observableArrayList();
    private IngredientRepository repository;

    public void initialize(){
		ingredientView.setItems(ingredientList);
        planTable.setItems(planList);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        fatColumn.setCellValueFactory(new PropertyValueFactory<>("fat"));
    }

	public void setupController(IngredientRepository repository){
        this.repository = repository;
		ingredientList.addAll(repository.getAllIngredients().stream()
				.map(Ingredient::getName)
				.collect(Collectors.toList()));

        ingredientView.setOnMouseClicked(e -> {
            if(e.getClickCount() != 2)
                return;

            listDoubleClickHandler();
        });
	}

    private void listDoubleClickHandler(){
        String name = ingredientView.getSelectionModel().getSelectedItem();
        Ingredient i = repository.getIngredientByName(name);

        planList.add(i);

    }
}
