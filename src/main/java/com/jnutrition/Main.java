package com.jnutrition;

import com.jnutrition.backend.IngredientRepository;
import com.jnutrition.backend.UnitRepository;
import com.jnutrition.backend.XMLUnitRepository;
import com.jnutrition.ui.JNutritionController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@ComponentScan(basePackages = "com.jnutrition")
@PropertySource(value = "classpath:/com/jnutrition/application.properties")
@Configuration
public class Main extends Application {

    private static List<String> parameters;
    private ApplicationContext context;

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

        context = new AnnotationConfigApplicationContext(Main.class);
        JNutritionController root = context.getBean(JNutritionController.class);

        Scene scene = new Scene((Parent) root.getView());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        parameters = Arrays.asList(args);
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        IngredientRepository ingredientRepository = context.getBean(IngredientRepository.class);
        ingredientRepository.close();
    }
}
