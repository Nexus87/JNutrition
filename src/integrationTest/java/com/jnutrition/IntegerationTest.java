package com.jnutrition;

import com.jnutrition.model.Unit;
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
        Unit chosenUnit = database.getUnitForIngredient(database.getItem(0));
        appRunner.setUnit(1, chosenUnit);
        appRunner.assertDataDisplayedWithUnit(1, chosenUnit);
    }

    @Test
    public void cancelUnitDialog_NothingIsAddedToTable(){
        appRunner.startApp(database);
        appRunner.doubleClickItem(database.getItem(0));
        appRunner.cancelUnitDialog();
        appRunner.assertTableIsEmpty();
    }

    @Test
    public void setUnit_DisplayedDataCorrespondToUnit(){
        appRunner.startApp(database);
        appRunner.doubleClickItem(database.getItem(0));
        Unit chosenUnit = database.getUnitForIngredient(database.getItem(0));
        appRunner.setUnit(100, chosenUnit);

        appRunner.assertTotalDataDisplayed(database.getItem(0), 100, chosenUnit);
    }

    @Test
    public void filterIngredientList_useExactName(){
        appRunner.startApp(database);
        appRunner.setFilter(database.getItem(0));

        appRunner.assertIngredientViewShowsData(database.getItem(0));
    }

    @Test
    public void addAndRemoveIngredient_IngredientIsRemovedFromList(){
        appRunner.startApp(database);
        appRunner.addIngredient(database.getItem(0));
        appRunner.assertTableShowsItem(database.getItem(0));

        appRunner.removeIngredient(database.getItem(0));

        appRunner.assertTableIsEmpty();
    }

    @Test
    public void alterPlanItemAmount_PlanItemsAmountChanged(){
        appRunner.startApp(database);
        appRunner.addIngredient(database.getItem(0));

        appRunner.doubleClickOnPlanItem(database.getItem(0));

        appRunner.setUnit(200, database.defaultUnit());

        appRunner.assertDataDisplayedWithUnit(200, database.defaultUnit());
    }
	@AfterTest
	public void tearDown(){
		database.Clean();
	}
}
