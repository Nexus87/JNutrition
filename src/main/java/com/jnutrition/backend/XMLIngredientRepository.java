package com.jnutrition.backend;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class XMLIngredientRepository implements IngredientRepository{

    @XmlRootElement
	private static class Ingredients{
		private static class Ingredient{
			@XmlElement
			public String name;
			@XmlElement
			public double kcal;
			@XmlElement
			public double protein;
			@XmlElement
			public double carbs;
			@XmlElement
			public double fat;

			com.jnutrition.backend.Ingredient toIngredient(){
				return new com.jnutrition.backend.Ingredient(name, kcal, protein, carbs, fat);
			}
		}

		@XmlElement
        public List<Ingredient> ingredient;
    }

	private List<Ingredient> ingredients = new ArrayList<>();
	
	public XMLIngredientRepository(InputStream inputStream) {

        Ingredients l = new Ingredients();
		l.ingredient = new ArrayList<>();
		try {
			JAXBContext context = JAXBContext.newInstance(Ingredients.class);
			Unmarshaller um = context.createUnmarshaller();
			l = (Ingredients) um.unmarshal(inputStream);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		 ingredients = l.ingredient.stream()
				.map(ingredient -> ingredient.toIngredient())
				.collect(Collectors.toList());

    }

	@Override
	public Collection<Ingredient> getAllIngredients() {
		return ingredients;
	}

	@Override
	public Ingredient getIngredientByName(String name) {
		return ingredients.stream().filter(ingredient -> ingredient.getName().equals(name)).findFirst().orElse(null);
	}

}
