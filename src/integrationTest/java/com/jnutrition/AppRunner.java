package com.jnutrition;

import com.jnutrition.backend.Ingredient;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import java.util.concurrent.TimeoutException;

import static org.testfx.matcher.control.ListViewMatchers.hasListCell;
import static org.testfx.matcher.control.TableViewMatchers.hasTableCell;
public class AppRunner {

	private static final String ListName = "ingredientView";
	private static final String TableName = "planTable";
	
	public void startApp(String filePath) {
		try {
			FxToolkit.registerPrimaryStage();
			FxToolkit.setupApplication(Main.class, filePath);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void assertShowsTestData(TestDatabase database) {
		for(String name : database.getItemsNames())
			FxAssert.verifyThat("#" + ListName, hasListCell(name));
	}

	public void doubleClickItem(Ingredient i) {
		FxRobot robot = new FxRobot();
		robot.doubleClickOn(i.getName());
	}

	public void assertTableShowsItem(Ingredient item) {
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getName()));
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getProtein()));
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getCarbs()));
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getFat()));
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getKcal()));
	}
}
