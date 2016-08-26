package com.jnutrition.backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XMLIngredientRepository implements IngredientRepository{

    @XmlRootElement
	private static class Ingredients{
		private static class Ingredient{
            @XmlElement
            String name;
			@XmlElement
            BigDecimal kcal;
            @XmlElement
            BigDecimal protein;
			@XmlElement
            BigDecimal carbs;
			@XmlElement
            BigDecimal fat;

			com.jnutrition.backend.Ingredient toIngredient(){
				return new com.jnutrition.backend.Ingredient(name, kcal, protein, carbs, fat);
			}
		}

		@XmlElement
        public List<Ingredient> ingredient;
    }

	private final ObservableList<Ingredient> ingredients;
	private final FilteredList<Ingredient> filteredIngredients;

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

        ingredients = FXCollections.observableArrayList(l.ingredient.stream()
				.map(Ingredients.Ingredient::toIngredient)
				.collect(Collectors.toList()));
        filteredIngredients = new FilteredList<>(ingredients);
    }

	@Override
	public ObservableList<Ingredient> getAllIngredients() {
		return filteredIngredients;
	}

    @Override
    public void setNameFilter(String name) {
        filteredIngredients.setPredicate(ingredient -> {
            if(name == null || name.isEmpty())
                return true;
            return ingredient.getName().equals(name);
        });
    }

    @Override
	public Ingredient getIngredientByName(String name) {
		return ingredients
				.stream()
				.filter(ingredient -> ingredient.getName().equals(name))
				.findFirst()
				.orElse(null);
	}

}
