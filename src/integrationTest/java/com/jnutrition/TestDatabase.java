package com.jnutrition;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestDatabase {
	
	private List<String> names;
	private File outputFile;
	public void setupDatabase() {
		names = Arrays.asList("Apple", "Water");
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
		return names;
	}

}
