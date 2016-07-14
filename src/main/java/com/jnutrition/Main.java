package com.jnutrition;

import com.jnutrition.backend.XMLIngredientRepository;
import com.jnutrition.backend.XMLUnitRepository;
import com.jnutrition.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        InputStream ingredients;
        InputStream units;
        if (getParameters().getRaw().size() == 2){
            ingredients = new FileInputStream(getParameters().getRaw().get(0));
            units = new FileInputStream(getParameters().getRaw().get(1));
        }
        else {
            ingredients = Main.class.getResourceAsStream("/IngredientDatabaseResources.xml");
            units = Main.class.getResourceAsStream("/UnitDatabaseResources.xml");
        }

        XMLIngredientRepository repository = new XMLIngredientRepository(ingredients);
        XMLUnitRepository unitRepository = new XMLUnitRepository(units);

    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainView.fxml"));

        AnchorPane root = loader.load();
        MainViewController controller = loader.getController();
        controller.setupController(repository, unitRepository);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
