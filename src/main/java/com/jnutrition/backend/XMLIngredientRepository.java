package com.jnutrition.backend;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XMLIngredientRepository implements IngredientRepository{

    @XmlRootElement
    @XmlSeeAlso(Ingredient.class)
	private static class Ingredients{
        private List<Ingredient> ingredients;

        @XmlElementRef
        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }
    }

	private List<Ingredient> ingredients = new ArrayList<>();
	
	public XMLIngredientRepository(InputStream inputStream) throws FileNotFoundException {

        Ingredients l = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Ingredients.class);
			Unmarshaller um = context.createUnmarshaller();
			l = (Ingredients) um.unmarshal(inputStream);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		ingredients = l.ingredients;

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
