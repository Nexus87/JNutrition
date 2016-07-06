package com.jnutrition.view;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.stream.Collectors;

public class MainViewController {
	@FXML
	private ListView<String> ingredientView;

	private final ObservableList<String> ingredientList = FXCollections.observableArrayList();

	public void initialize(){
		ingredientView.setItems(ingredientList);
	}

	public void setupController(IngredientRepository repository){
		ingredientList.addAll(repository.getAllIngredients().stream()
				.map(Ingredient::getName)
				.collect(Collectors.toList()));
	}
}
