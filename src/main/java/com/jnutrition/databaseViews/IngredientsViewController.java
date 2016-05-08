package com.jnutrition.databaseViews;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Ingredient, String> nameColumn;
    @FXML
    private TableColumn<Ingredient, Double> kcalColumn;
    @FXML
    private TableColumn<Ingredient, Double> proteinColumn;
    @FXML
    private TableColumn<Ingredient, Double> carbsColumn;
    @FXML
    private TableColumn<Ingredient, Double> fatColumn;

    ObservableList<Ingredient> dataList = FXCollections.observableArrayList(
//            new Ingredient("Name1", 10, 1, 34, 42),
//            new Ingredient("Name2", 16, 23, 43, 42),
//            new Ingredient("Name3", 15, 21, 43, 21),
//            new Ingredient("Name4", 11, 13, 43, 22),
//            new Ingredient("Name5", 13, 1, 23, 23)

    );

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        fatColumn.setCellValueFactory(new PropertyValueFactory<>("fat"));

        tableView.setItems(dataList);
    }

    public void init(IngredientImageProvider imageProvider, IngredientProvider database) {
    }
}
