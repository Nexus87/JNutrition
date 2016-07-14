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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String filePath = getParameters().getRaw().get(0);
        String unitsFilePath = getParameters().getRaw().get(1);

        XMLIngredientRepository repository = new XMLIngredientRepository(new FileInputStream(filePath));
        XMLUnitRepository unitRepository = new XMLUnitRepository(new FileInputStream(unitsFilePath));

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
        if(args.length != 2)
            args = new String[]{
                    ClassLoader.getSystemResource("IngredientDatabaseResources.xml").getFile(),
                    ClassLoader.getSystemResource("UnitDatabaseResources.xml").getFile()
            };

        launch(args);
    }
}
