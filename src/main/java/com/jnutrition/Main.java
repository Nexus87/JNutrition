package com.jnutrition;

import com.jnutrition.backend.XMLIngredientRepository;
import com.jnutrition.backend.XMLUnitRepository;
import com.jnutrition.view.MainViewController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileInputStream;
import java.io.InputStream;
@ComponentScan(basePackages = "com.jnutrition")
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        InputStream ingredients;
        InputStream units;
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
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

        MainViewController root = context.getBean(MainViewController.class);

        root.setupController(repository, unitRepository);

        Scene scene = new Scene((Parent) root.getView());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
