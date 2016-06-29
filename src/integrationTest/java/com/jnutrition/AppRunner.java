package com.jnutrition;

import java.util.concurrent.TimeoutException;

import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import static org.testfx.matcher.control.ListViewMatchers.*;
public class AppRunner {

	private static final String ListName = "ListView";

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

}
