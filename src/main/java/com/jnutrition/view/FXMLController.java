package com.jnutrition.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class FXMLController {
    protected String fxmlFilePath;
    private Node view;

    public void loadFXML(){
        try (InputStream fxmlStream = getClass().getResourceAsStream(fxmlFilePath)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(this);
            this.view = (loader.load(fxmlStream));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public Node getView(){
        return view;
    }
}
