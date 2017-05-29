package testcases;


import java.lang.reflect.Method;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class Login extends TestBase {
	
		
	@BeforeMethod
	public void verifyTestcaseMode(Method m){
		
		if(!TestUtil.verifyTestCaseMode(m.getName())){
			log.debug(m.getName()+": execution skipped as the runmode is set to no");
			throw new SkipException("Test case execution skipped as the runmode is set to no");
		}
	}
	
	
	/*@Test(dataProvider="getData")
	public void one(String user, String pass, String mode){			
		System.out.println(config.getProperty("browserName"));
		
		System.out.println(user);
		System.out.println(pass);
		System.out.println(mode);
						
		//initiateBrowser();
		advanto_Input("peers", "id","email");
		advanto_Input("peers", "id","pass");
		//obj.advanto_Click("peers", "id","email");
		//obj.advanto_verifyText(locator, "");
	}*/
	
@Test(dataProvider="getData")
	
	public void TC_001(String keyword, String locator, String data){
		
		switch(keyword){
		
		case "launchBrowser":
			launchBrowser();
			break;
			
		case "enterText":
			type(locator,data);
			break;

		case "clickButton":
			click(locator);
			break;
			
		/*case "verifyText":
			verifyText(locator, data);
			break;*/
		}

		//System.out.println("Login verified successfully");
	}
	

	
	
	@DataProvider
	public Object[][] getData(Method m){
		Object[][] myData = TestUtil.getData(m.getName());
		return myData;
		
	}
	
	
}
