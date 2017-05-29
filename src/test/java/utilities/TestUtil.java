package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



import base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotName;
	public static void captureScreenshot() throws IOException {

		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		Date d = new Date();
		screenshotName=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));

	}
	
	
	public static By getLocator(String ElementName) throws Exception {
		// Read value using the logical name as Key
		String locator = OR.getProperty(ElementName);
		// Split the value which contains locator type and locator value
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];
		// Return a instance of By class based on type of locator
		if (locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("Locator type '" + locatorType
					+ "' not defined!!");
	}

	
public static boolean verifyTestCaseMode(String myTestCase) {
		
		boolean testCaseMode = false;
		
		for(int i=1;i<excel.getRowCount("Sheet1");i++){
		
			if(excel.getCellData("Sheet1", "ID", i).toLowerCase().matches(myTestCase.toLowerCase())){
				
				if(excel.getCellData("Sheet1", "Mode", i).toLowerCase().matches("y"))
					testCaseMode = true;
				else
					testCaseMode = false;				
			}
		}			
		return testCaseMode;	
	}

	public static Object[][] getData(String sheetName) {

		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols-2];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 2; colNum < cols; colNum++) {

				data[rowNum - 2][colNum-2] = excel.getCellData(sheetName, colNum, rowNum);

			}

		}

		return data;

	}

}
