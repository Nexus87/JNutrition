package com.jnutrition;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.Unit;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class TestDatabase {
	
	private List<Ingredient> ingredients;
	private String ingredientsFile;
	private String unitsFile;
	void setupDatabase() {
		ingredients = Arrays.asList(
				new Ingredient("Apple", 3.0, 2.0, 1.0, 0.0),
				new Ingredient("Water", 1, 1, 1, 0),
				new Ingredient("Egg", new BigDecimal("3.3"), new BigDecimal("2.2"), new BigDecimal("1.12"), new BigDecimal("3.0"))
		);
		ingredientsFile = ClassLoader.getSystemResource("IngredientDatabaseResources.xml").getFile();
		unitsFile = ClassLoader.getSystemResource("UnitDatabaseResources.xml").getFile();
	}

	String getFilePath() {
		return ingredientsFile;
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
		return Unit.GRAM;
	}

    public Unit getUnitForIngredient(Ingredient ingredient) {
        return new Unit("pound", 50);
    }

	public String getUnitDatabasePath() {
		return unitsFile;
	}
}
