package com.jnutrition;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IntegerationTest {
	private TestDatabase database;
	private AppRunner appRunner;

	@BeforeTest
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

    @Test
    public void startApp_DataAreAllZero(){
        database.setupDatabase();
        appRunner.startApp(database.getFilePath());
        appRunner.assertDisplayedTotalKCal(0);
        appRunner.assertDisplayedTotalProtein(0);
        appRunner.assertDisplayedTotalCarbs(0);
        appRunner.assertDisplayedTotalFat(0);
    }

    @Test
    public void addOneIngredient_IngredientsDataAreShown(){
        database.setupDatabase();
        appRunner.startApp(database.getFilePath());
        appRunner.doubleClickItem(database.getItem(1));

        appRunner.assertDataDisplayed(database.getItem(1));
    }
	@AfterTest
	public void tearDown(){
		database.Clean();
	}
}
