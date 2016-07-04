package com.jnutrition;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntegerationTest {
	private TestDatabase database;
	private AppRunner appRunner;

	@Before
	public void setup(){
		database = new TestDatabase();
		appRunner = new AppRunner();
	}
	
	@Test
	public void loadFileAndDisplayInList(){
		database.setupDatabase();
		appRunner.startApp(database.getFilePath());
		appRunner.assertShowsTestData(database);
	}
	
	@Test
	public void clickOnIngredient_ItemShowsInTable(){
		database.setupDatabase();
		appRunner.startApp(database.getFilePath());
		appRunner.doubleClickItem(database.getItem(0));
		appRunner.assertTableShowsItem(database.getItem(0));
	}
	@After
	public void tearDown(){
		database.Clean();
	}
}
