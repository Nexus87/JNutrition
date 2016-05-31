package com.jnutrition;

import com.jnutrition.backend.InMemoryIngredientRepository;
import com.jnutrition.backend.Ingredient;
import com.jnutrition.databaseViews.IngredientEditorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by nexxuz0 on 24.04.2016.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("databaseViews/IngredientEditor.fxml"));


        AnchorPane root = loader.load();
        IngredientEditorController controller = loader.getController();

        InMemoryIngredientRepository store = new InMemoryIngredientRepository();
        store.insertIngredient(new Ingredient("Name", 1, 2, 3, 4));
        store.insertIngredient(new Ingredient("Name2", 1, 2, 3, 4));
        store.insertIngredient(new Ingredient("Name3", 1, 2, 3, 4));
        //controller.init(new DefaultIngredientImageProvider(), store);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main()
    {
        launch();
    }
}
