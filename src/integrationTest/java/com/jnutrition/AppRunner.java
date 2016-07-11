package com.jnutrition;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.Unit;
import javafx.scene.control.ButtonType;
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
    private static final String AmountFieldName = "amountField";
    private static final String UnitFieldName = "unitField";

    void startApp(TestDatabase database) {
		try {
			FxToolkit.registerPrimaryStage();
			FxToolkit.setupApplication(Main.class, database.getFilePath());
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

    public void assertDataDisplayed(Ingredient item) {
        assertDisplayedTotalCarbs(item.getCarbs());
        assertDisplayedTotalFat(item.getFat());
        assertDisplayedTotalKCal(item.getKcal());
        assertDisplayedTotalProtein(item.getProtein());
    }

    public void setUnit(double amount, Unit unit) {
        FxRobot robot = new FxRobot();
        robot.clickOn("#" + AmountFieldName);
        robot.write(Double.toString(amount));
        robot.clickOn("#" + UnitFieldName);
        robot.write(unit.toString());
        robot.clickOn(ButtonType.OK.getText());
    }

    public void assertDataDisplayedWithUnit(double amount, Unit unit) {
        FxAssert.verifyThat("#" + TableName, hasTableCell(amount + " " + unit));
    }

    private static class UnitMatcher{
        private Unit unit;
        private Double amount;

        private UnitMatcher(Double amount, Unit unit) {
            this.unit = unit;
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof String))
                return false;

            String text = (String) o;

            return text.contains(unit.toString()) && text.contains(amount.toString());
        }
    }
}
