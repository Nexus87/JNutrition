package com.jnutrition;

import com.jnutrition.ui.JNutritionController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManagerFactory;

@ComponentScan(basePackages = "com.jnutrition")
@PropertySource(value = "classpath:/com/jnutrition/application.properties")
@Configuration
public class Main extends Application {

    private ApplicationContext context;

    @Override
    public void start(Stage primaryStage) throws Exception {

        context = new AnnotationConfigApplicationContext(Main.class);
        JNutritionController root = context.getBean(JNutritionController.class);

        Scene scene = new Scene((Parent) root.getView());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        entityManagerFactory.close();
    }
}
