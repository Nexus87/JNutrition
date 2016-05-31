package com.jnutrition.databaseViews;

import com.jnutrition.Main;
import com.jnutrition.backend.InMemoryIngredientRepository;
import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientRepository;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IngredientEditorTest extends ApplicationTest {
    private static final double DELTA = 1e-15;
    private IngredientEditorController controller;
    private IngredientRepository repository;

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("databaseViews/IngredientEditor.fxml"));
        repository = new InMemoryIngredientRepository();
        try {
            parent = loader.load();
            controller = loader.getController();
            controller.init(repository);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void showIngredientTest(){
        Ingredient testIngredient = new Ingredient("Name", 1.0f, 2.0f, 3.0f, 4.0f);
        controller.setIngredient(testIngredient);

        TextField name = lookup("#nameField").queryFirst();
        double kcal = getValue("#kcalField");
        double protein = getValue("#proteinField");
        double carbs = getValue("#carbsField");
        double fat = getValue("#fatField");


        assertEquals(name.getText(), testIngredient.getName());
        assertEquals(kcal, testIngredient.getKcal(), DELTA);
        assertEquals(protein, testIngredient.getProtein(), DELTA);
        assertEquals(carbs, testIngredient.getCarbs(), DELTA);
        assertEquals(fat, testIngredient.getFat(), DELTA);
    }

    @Test
    public void saveIngredientsTest(){
        Ingredient testIngredient = new Ingredient("Name", 1.0f, 2.0f, 3.0f, 4.0f);
        controller.setIngredient(testIngredient);

        clickOn("#saveButton", MouseButton.PRIMARY);

        Ingredient savedIngredient = repository.getIngredient(testIngredient.getName());
        assertEquals(testIngredient, savedIngredient);
    }

    @Test
    public void changeIngredientTest(){
        Ingredient testIngredient = new Ingredient("Name", 1, 2, 3, 4);
        Ingredient editedIngredient = new Ingredient("Name", 1, 3, 3, 4);
        repository.insertIngredient(testIngredient);
        controller.setIngredient(testIngredient);

        setValue("#proteinField", "3");
        clickOn("#saveButton", MouseButton.PRIMARY);

        Ingredient returnValue = repository.getIngredient(editedIngredient.getName());

        assertEquals(editedIngredient, returnValue);
    }

    private double getValue(String fieldName){
        return Double.parseDouble(lookup(fieldName).<TextField>queryFirst().getText());
    }

    private void setValue(String fieldName, Object value){
        lookup(fieldName).<TextField>queryFirst().setText(value.toString());
    }
}
