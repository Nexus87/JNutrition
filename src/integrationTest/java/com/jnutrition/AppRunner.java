package com.jnutrition;

import com.jnutrition.backend.Ingredient;
import com.jnutrition.backend.Unit;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import java.util.concurrent.TimeoutException;

import static com.jnutrition.ListViewMatcher.hasCellThatContainsText;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;
import static org.testfx.matcher.control.ListViewMatchers.hasListCell;
import static org.testng.Assert.assertEquals;

class AppRunner {

	private static final String ListName = "ingredientView";
	private static final String PlanListName = "planList";
    private static final String KCalLableName = "kcalLabel";
    private static final String ProteinLableName = "proteinLabel";
    private static final String CarbsLableName = "carbsLabel";
    private static final String FatLableName = "fatLabel";
    private static final String AmountFieldName = "amountField";
    private static final String UnitFieldName = "unitField";

    void startApp(TestDatabase database) {
		try {
			FxToolkit.registerPrimaryStage();
			FxToolkit.setupApplication(Main.class, database.getFilePath(), database.getUnitDatabasePath());
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
		FxAssert.verifyThat("#" + PlanListName, hasCellThatContainsText(item.getName()));
		FxAssert.verifyThat("#" + PlanListName, hasCellThatContainsText(item.getProtein()));
		FxAssert.verifyThat("#" + PlanListName, hasCellThatContainsText(item.getCarbs()));
		FxAssert.verifyThat("#" + PlanListName, hasCellThatContainsText(item.getFat()));
		FxAssert.verifyThat("#" + PlanListName, hasCellThatContainsText(item.getKcal()));
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
        assertDisplayedTotalCarbs(item.getCarbs().doubleValue());
        assertDisplayedTotalFat(item.getFat().doubleValue());
        assertDisplayedTotalKCal(item.getKcal().doubleValue());
        assertDisplayedTotalProtein(item.getProtein().doubleValue());
    }

    public void setUnit(double amount, Unit unit) {
        FxRobot robot = new FxRobot();
        robot.clickOn("#" + AmountFieldName);
        robot.write(Double.toString(amount));
        robot.clickOn("#" + UnitFieldName);
        robot.clickOn(unit.toString());
        robot.clickOn(ButtonType.OK.getText());
    }

    public void assertDataDisplayedWithUnit(double amount, Unit unit) {
        FxAssert.verifyThat("#" + PlanListName, hasCellThatContainsText(amount + "" + unit));
    }

    public void cancelUnitDialog() {
        FxRobot robot = new FxRobot();
        robot.clickOn(ButtonType.CANCEL.getText());
    }

    public void assertTableIsEmpty() {
        FxAssert.verifyThat("#" + PlanListName, hasItems(0));
    }

    public void assertTotalDataDisplayed(Ingredient item, double amount, Unit unit) {
        assertDisplayedTotalFat(amount * unit.inGram() * item.getFat().doubleValue()/100);
        assertDisplayedTotalCarbs(amount * unit.inGram() * item.getCarbs().doubleValue()/100);
        assertDisplayedTotalProtein(amount * unit.inGram() * item.getProtein().doubleValue()/100);
        assertDisplayedTotalKCal(amount * unit.inGram() * item.getKcal().doubleValue()/100);
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
