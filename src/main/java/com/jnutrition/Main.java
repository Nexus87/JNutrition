package com.jnutrition;

import com.jnutrition.backend.IngredientRepository;
import com.jnutrition.backend.UnitRepository;
import com.jnutrition.backend.XMLIngredientRepository;
import com.jnutrition.backend.XMLUnitRepository;
import com.jnutrition.view.MainViewController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@ComponentScan(basePackages = "com.jnutrition")
@Configuration
public class Main extends Application {

    private XMLIngredientRepository ingredientRepository;
    private XMLUnitRepository unitRepository;
    private static List<String> parameters;

    @Bean
    public IngredientRepository ingredientRepository() {
        InputStream ingredients = null;

        if (parameters.size() == 2) {
            try {
                ingredients = new FileInputStream(parameters.get(0));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            ingredients = Main.class.getResourceAsStream("/IngredientDatabaseResources.xml");
        }
        return new XMLIngredientRepository(ingredients);
    }

    @Bean
    public UnitRepository unitRepository() {
        InputStream units = null;

        if (parameters.size() == 2) {
            try {
                units = new FileInputStream(parameters.get(1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            units = Main.class.getResourceAsStream("/UnitDatabaseResources.xml");
        }
        return new XMLUnitRepository(units);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        MainViewController root = context.getBean(MainViewController.class);

        root.setupController(ingredientRepository, unitRepository);

        Scene scene = new Scene((Parent) root.getView());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        parameters = Arrays.asList(args);
        launch(args);
    }
}
