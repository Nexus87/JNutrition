package com.jnutrition.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class XMLIngredientRepository implements IngredientRepository{

	private List<Ingredient> ingredients = new ArrayList<>();
	
	public XMLIngredientRepository(String filePath) {
		Scanner s = new Scanner(filePath);
		while(s.hasNextLine()){
			String line = s.nextLine();
			if(line.contains("<Name>"))
				ingredients.add(new Ingredient(line.replace("<Name>", "").replace("</Name>", "").trim(), 0, 0, 0, 0));
		}
		
		s.close();
	}

	@Override
	public Collection<Ingredient> getAllIngredients() {
		return ingredients;
	}

	@Override
	public Ingredient getIngredient(String name) {
		return null;
	}

	@Override
	public Collection<Ingredient> searchIngredients(String searchString) {
		return null;
	}

	@Override
	public void insertIngredient(Ingredient ingredient) {
		
	}

	@Override
	public void mergeIngredient(Ingredient ingredient) {
		
	}

	@Override
	public void addRepositoryChangeListener(IngredientsChangedHandler listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRepositoryChangedHandler(IngredientsChangedHandler listener) {
		// TODO Auto-generated method stub
		
	}

}
