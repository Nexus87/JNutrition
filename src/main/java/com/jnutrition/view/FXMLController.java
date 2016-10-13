package com.jnutrition.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.InitializingBean;

import java.io.InputStream;

public abstract class FXMLController implements InitializingBean {
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

    @Override
    public void afterPropertiesSet() throws Exception {
        loadFXML();
    }

    public Node getView(){
        return view;
    }
}
