package com.jnutrition;

import com.jnutrition.backend.XMLIngredientRepository;
import com.jnutrition.view.MainViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {
	private static String filePath;
    @Override
    public void start(Stage primaryStage) throws Exception {
        String filePath = getParameters().getRaw().get(0);
        XMLIngredientRepository repository = new XMLIngredientRepository(new FileInputStream(filePath));
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
        launch(args);
    }
}
