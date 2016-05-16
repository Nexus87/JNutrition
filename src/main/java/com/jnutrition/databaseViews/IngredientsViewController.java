package com.jnutrition.databaseViews;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientRepository;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class IngredientsViewController
{
    @FXML
    private TableView<Ingredient> tableView;
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
    private TextField filterBox;

    ObservableList<Ingredient> dataList = FXCollections.observableArrayList();
    private IngredientImageProvider imageProvider;
    private IngredientRepository provider;

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        fatColumn.setCellValueFactory(new PropertyValueFactory<>("fat"));

        tableView.setItems(dataList);
    }

    public void init(IngredientImageProvider imageProvider, IngredientRepository provider) {
        this.imageProvider = imageProvider;
        this.provider = provider;

        nameColumn.setCellFactory(param -> new ImageTableCell<>(imageProvider));
        dataList.addAll(provider.getAllIngredients());

        filterBox.textProperty().addListener(this::textBoxListener);
    }

    private void textBoxListener(ObservableValue<? extends String> property, String oldValue, String newValue){
        dataList.clear();
        dataList.addAll(provider.searchIngredients(newValue));
    }


}
