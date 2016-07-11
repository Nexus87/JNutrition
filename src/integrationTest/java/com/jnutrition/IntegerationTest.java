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
        database.setupDatabase();
	}
	
	@Test
	public void loadFileAndDisplayInList(){
		appRunner.startApp(database);
		appRunner.assertShowsTestData(database);
	}
	
	@Test
	public void clickOnIngredient_ItemShowsInTable(){
		appRunner.startApp(database);
		appRunner.doubleClickItem(database.getItem(0));
        appRunner.setUnit(100, database.defaultUnit());
		appRunner.assertTableShowsItem(database.getItem(0));
	}

    @Test
    public void startApp_DataAreAllZero(){
        appRunner.startApp(database);
        appRunner.assertDisplayedTotalKCal(0);
        appRunner.assertDisplayedTotalProtein(0);
        appRunner.assertDisplayedTotalCarbs(0);
        appRunner.assertDisplayedTotalFat(0);
    }

    @Test
    public void addOneIngredient_IngredientsDataAreShown(){
        appRunner.startApp(database);
        appRunner.doubleClickItem(database.getItem(1));
        appRunner.setUnit(100, database.defaultUnit());
        appRunner.assertDataDisplayed(database.getItem(1));
    }

	@Test
    public void addOneIngredientWithUnits(){
        appRunner.startApp(database);
        appRunner.doubleClickItem(database.getItem(0));
        appRunner.setUnit(1, database.Unit());
        appRunner.assertDataDisplayedWithUnit(1, database.Unit());
    }

    @Test
    public void cancelUnitDialog_NothingIsAddedToTable(){
        appRunner.startApp(database);
        appRunner.doubleClickItem(database.getItem(0));
        appRunner.cancelUnitDialog();
        appRunner.assertTableIsEmpty();
    }
	@AfterTest
	public void tearDown(){
		database.Clean();
	}
}
