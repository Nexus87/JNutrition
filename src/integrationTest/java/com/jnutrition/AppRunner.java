package com.jnutrition;

import com.jnutrition.backend.Ingredient;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testng.Assert;

import java.util.concurrent.TimeoutException;

import static org.testfx.matcher.control.ListViewMatchers.hasListCell;
import static org.testfx.matcher.control.TableViewMatchers.hasTableCell;
import static org.testng.Assert.assertEquals;

class AppRunner {

	private static final String ListName = "ingredientView";
	private static final String TableName = "planTable";
    private static final String KCalLableName = "kcalLabel";
    private static final String ProteinLableName = "proteinLabel";
    private static final String CarbsLableName = "carbsLabel";
    private static final String FatLableName = "fatLabel";

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

    public void assertDisplayedTotalKCal(double totalKCal) {
        assertLabelShowsNumber(KCalLableName, totalKCal);
    }

    private void assertLabelShowsNumber(String labelName, double expectedNumber) {
        FxRobot robot = new FxRobot();
        Label label = robot.lookup("#" + labelName).queryFirst();
        if(label == null)
            throw new NodeNotFoundException(labelName);

        double number = Double.parseDouble(label.getText());
        assertEquals(number, expectedNumber, 1e-10);
    }

    public void assertDisplayedTotalProtein(double totalProtein) {
        assertLabelShowsNumber(ProteinLableName, totalProtein);
    }

    public void assertDisplayedTotalCarbs(double totalCarbs) {
        assertLabelShowsNumber(CarbsLableName, totalCarbs);
    }

    public void assertDisplayedTotalFat(double totalFat) {
        assertLabelShowsNumber(FatLableName, totalFat);
    }
}
