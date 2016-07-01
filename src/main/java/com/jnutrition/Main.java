package com.jnutrition;

import com.jnutrition.backend.InMemoryIngredientRepository;
import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.XMLIngredientRepository;
import com.jnutrition.databaseViews.IngredientEditorController;
import com.jnutrition.view.MainViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by nexxuz0 on 24.04.2016.
 */
public class Main extends Application {
	private static String filePath;
    @Override
    public void start(Stage primaryStage) throws Exception {
        XMLIngredientRepository repository = new XMLIngredientRepository(filePath);
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainView.fxml"));

        AnchorPane root = loader.load();
        MainViewController controller = loader.getController();
        controller.setupController(repository);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
    	filePath = args[0];
        launch();
    }
}
