package com.jnutrition.databaseViews;


import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientRepository;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.NumberFormat;

public class IngredientEditorController {
    private static class IngredientWrapper{
        private final SimpleStringProperty name = new SimpleStringProperty();
        private final SimpleDoubleProperty kcal = new SimpleDoubleProperty();
        private final SimpleDoubleProperty protein = new SimpleDoubleProperty();
        private final SimpleDoubleProperty carbs = new SimpleDoubleProperty();
        private final SimpleDoubleProperty fat = new SimpleDoubleProperty();

        public void setIngredient(Ingredient ingredient){
            name.set(ingredient.getName());
            kcal.set(ingredient.getKcal());
            protein.set(ingredient.getProtein());
            carbs.set(ingredient.getCarbs());
            fat.set(ingredient.getFat());
        }
        public double getFat() {
            return fat.get();
        }

        public SimpleDoubleProperty fatProperty() {
            return fat;
        }

        public double getCarbs() {
            return carbs.get();
        }

        public SimpleDoubleProperty carbsProperty() {
            return carbs;
        }

        public double getProtein() {
            return protein.get();
        }

        public SimpleDoubleProperty proteinProperty() {
            return protein;
        }

        public double getKcal() {
            return kcal.get();
        }

        public SimpleDoubleProperty kcalProperty() {
            return kcal;
        }

        public SimpleStringProperty nameProperty(){
            return name;
        }

        public Ingredient toIngredient(){
            return new Ingredient(name.get(), kcal.get(), protein.get(), carbs.get(), fat.get());
        }
    }
    @FXML
    private TextField nameField;
    @FXML
    private TextField kcalField;
    @FXML
    private TextField proteinField;
    @FXML
    private TextField carbsField;
    @FXML
    private TextField fatField;
    @FXML
    private Button saveButton;

    private final IngredientWrapper ingredient = new IngredientWrapper();
    private IngredientRepository repository;

    public void init(IngredientRepository repository){
        this.repository = repository;
    }

    public void initialize(){
        nameField.textProperty().bindBidirectional(ingredient.nameProperty());
        kcalField.textProperty().bindBidirectional(ingredient.kcalProperty(), NumberFormat.getNumberInstance());
        proteinField.textProperty().bindBidirectional(ingredient.proteinProperty(), NumberFormat.getNumberInstance());
        carbsField.textProperty().bindBidirectional(ingredient.carbsProperty(), NumberFormat.getNumberInstance());
        fatField.textProperty().bindBidirectional(ingredient.fatProperty(), NumberFormat.getNumberInstance());
    }

    @FXML
    private void saveClicked(){
        repository.mergeIngredient(ingredient.toIngredient());
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient.setIngredient(ingredient);
    }
}
