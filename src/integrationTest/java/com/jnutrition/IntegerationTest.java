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
	public void displayItemsTest(){
		database.setupDatabase();
		appRunner.startApp(database.getFilePath());
		appRunner.assertShowsTestData(database);
	}
	
	@After
	public void tearDown(){
		database.Clean();
	}
}
