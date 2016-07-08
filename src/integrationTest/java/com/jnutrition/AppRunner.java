package com.jnutrition;

import com.jnutrition.backend.Ingredient;
import javafx.scene.control.Label;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

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

    void startApp(String filePath) {
		try {
			FxToolkit.registerPrimaryStage();
			FxToolkit.setupApplication(Main.class, filePath);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void assertShowsTestData(TestDatabase database) {
		for(Ingredient name : database.getItems())
			FxAssert.verifyThat("#" + ListName, hasListCell(name));
	}

	void doubleClickItem(Ingredient i) {
		FxRobot robot = new FxRobot();
		robot.doubleClickOn(i.getName());
	}

	void assertTableShowsItem(Ingredient item) {
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getName()));
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getProtein()));
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getCarbs()));
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getFat()));
		FxAssert.verifyThat("#" + TableName, hasTableCell(item.getKcal()));
	}

    void assertDisplayedTotalKCal(double totalKCal) {
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

    void assertDisplayedTotalProtein(double totalProtein) {
        assertLabelShowsNumber(ProteinLableName, totalProtein);
    }

    void assertDisplayedTotalCarbs(double totalCarbs) {
        assertLabelShowsNumber(CarbsLableName, totalCarbs);
    }

    void assertDisplayedTotalFat(double totalFat) {
        assertLabelShowsNumber(FatLableName, totalFat);
    }
}
