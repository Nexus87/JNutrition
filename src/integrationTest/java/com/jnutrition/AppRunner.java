package com.jnutrition;

import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import com.jnutrition.backend.Ingredient;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import static org.testfx.matcher.control.ListViewMatchers.*;
import static org.testfx.matcher.control.TableViewMatchers.*;
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
		FxAssert.verifyThat("#" + TableName, hasTableCell(item));
	}
}
