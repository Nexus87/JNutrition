package com.jnutrition.databaseViews;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientProvider;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.util.Collection;
import java.util.List;

/**
 * Created by nexxuz0 on 23.04.2016.
 */
public class IngredientsViewController
{
    @FXML
    private TableView<Ingredient> tableView;
    @FXML
    private TableColumn<Ingredient, String> imageColumn;
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

    ObservableList<Ingredient> dataList = FXCollections.observableArrayList();
    private IngredientImageProvider imageProvider;
    private IngredientProvider provider;

    public void initialize() {
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        fatColumn.setCellValueFactory(new PropertyValueFactory<>("fat"));

        tableView.setItems(dataList);
    }

    public void init(IngredientImageProvider imageProvider, IngredientProvider provider) {
        this.imageProvider = imageProvider;
        this.provider = provider;
        imageColumn.setCellFactory(param -> new ImageTableCell<>(imageProvider));
        dataList.addAll(provider.getAllIngredients());
    }
}
