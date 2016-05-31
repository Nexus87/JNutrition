package com.jnutrition.databaseViews;

import com.jnutrition.Main;
import com.jnutrition.backend.InMemoryIngredientRepository;
import com.jnutrition.backend.Ingredient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class IngredientTableTest extends ApplicationTest{
    IngredientsViewController controller;
    InMemoryIngredientRepository provider;
    IngredientImageProvider imageProvider;
    TableView<Ingredient> table;

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = null;
        FXMLLoader loader = new FXMLLoader();
        URL path = Main.class.getResource("databaseViews/IngredientsView.fxml");
        loader.setLocation(path);
        try {
            parent = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.show();
    }

    @Before
    public void setUp(){
        table = lookup("#tableView").queryFirst();
        imageProvider = new DefaultIngredientImageProvider();
        provider = new InMemoryIngredientRepository();
    }

    @Test
    public void setData_DataVisibleInTable(){
        List<Ingredient> ingredientList = new LinkedList<>();
        ingredientList.add(new Ingredient("name1", 1.0d, 2.0d, 3.0d, 4.0d));
        ingredientList.add(new Ingredient("name2", 5.0d, 6.0d, 7.0d, 8.0d));
        ingredientList.add(new Ingredient("name3", 9.0d, 10.0d, 11.0d, 12.0d));

        for( Ingredient i : ingredientList)
            provider.insertIngredient(i);

        controller.init(imageProvider, provider);

        for(int i = 0; i < ingredientList.size(); i++)
            assertTableRow(ingredientList.get(i), i);
    }

    @Test
    public void filterBoxTest(){
        TextField filter = lookup("#filterBox").queryFirst();
        List<Ingredient> ingredientList = new LinkedList<>();
        ingredientList.add(new Ingredient("name1", 1.0d, 2.0d, 3.0d, 4.0d));
        ingredientList.add(new Ingredient("name2", 5.0d, 6.0d, 7.0d, 8.0d));
        ingredientList.add(new Ingredient("name3", 9.0d, 10.0d, 11.0d, 12.0d));

        for( Ingredient i : ingredientList)
            provider.insertIngredient(i);

        controller.init(imageProvider, provider);
        filter.setText("name3");

        Assert.assertEquals(1, table.getItems().size());
        assertTableRow(ingredientList.get(2), 0);

    }
    private void assertTableRow(Ingredient ingredient, int row) {
        Assert.assertEquals(ingredient.getName(), getCellValue("nameColumn", row));
        Assert.assertEquals(ingredient.getKcal(), getCellValue("kcalColumn", row));
        Assert.assertEquals(ingredient.getProtein(), getCellValue("proteinColumn", row));
        Assert.assertEquals(ingredient.getCarbs(), getCellValue("carbsColumn", row));
        Assert.assertEquals(ingredient.getFat(), getCellValue("fatColumn", row));
    }

    private TableColumn findColumn(String id){
        return  table.getColumns().stream()
                .filter(column -> id.equals(column.getId()))
                .findFirst()
                .get();
    }

    private Object getCellValue(String columnId, int row){
        return findColumn(columnId).getCellData(row);
    }
}
