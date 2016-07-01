package com.jnutrition.view;

import java.util.stream.Collectors;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.IngredientRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainViewController {
	@FXML
	private ListView<String> ingredientView;
	private ObservableList<String> ingredientList = FXCollections.observableArrayList();
	private IngredientRepository repository;
	
	public void initialze(){
		ingredientList.addAll(repository.getAllIngredients().stream()
				.map(Ingredient::getName)
				.collect(Collectors.toList()));
		ingredientView.setItems(ingredientList);
	}
	
	public void setupController(IngredientRepository repository){
		this.repository = repository;
	}
}
