package com.jnutrition;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.Unit;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class TestDatabase {
	
	private List<Ingredient> ingredients;
	private File outputFile;
	void setupDatabase() {
		ingredients = Arrays.asList(
				new Ingredient("Apple", 3.0, 2.0, 1.0, 0.0),
				new Ingredient("Water", 1, 1, 1, 0),
				new Ingredient("Egg", 3.3, 2.2, 1.12, 3.0)
		);
		try {
			outputFile = new File(ClassLoader.getSystemResource("IngredientDatabaseResources.xml").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	String getFilePath() {
		return outputFile.getAbsolutePath();
	}

	void Clean() {
	}

	public Collection<String> getItemsNames() {
		return ingredients.stream().map(Ingredient::getName).collect(Collectors.toList());
	}

	Collection<Ingredient> getItems(){
		return ingredients;
	}

	Ingredient getItem(int i) {
		return ingredients.get(i);
	}

	public Unit defaultUnit() {
		return new Unit("g");
	}

    public Unit Unit() {
        return new Unit("Unit");
    }
}
