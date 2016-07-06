package com.jnutrition;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.jnutrition.backend.Ingredient;

public class TestDatabase {
	
	private List<Ingredient> ingredients;
	private File outputFile;
	public void setupDatabase() {
		ingredients = Arrays.asList(new Ingredient("Apple", 3.0, 2.0, 1.0, 0.0), new Ingredient("Water", 0, 0, 0, 0));
		try {
			outputFile = new File(ClassLoader.getSystemResource("IngredientDatabaseResources.xml").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFilePath() {
		return outputFile.getAbsolutePath();
	}

	public void Clean() {
	}

	public Collection<String> getItemsNames() {
		return ingredients.stream().map(Ingredient::getName).collect(Collectors.toList());
	}

	public Ingredient getItem(int i) {
		return ingredients.get(i);
	}

}
