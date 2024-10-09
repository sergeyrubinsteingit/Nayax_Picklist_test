
package ProgramFiles.TestUsedClasses;

import java.awt.AWTException;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ProgramFiles.GloballyUsedClasses.CreateHtmlReportFiles;
import ProgramFiles.GloballyUsedClasses.NetTrafficControl;
import ProgramFiles.GloballyUsedClasses.TestDataStorage;
import ProgramFiles.TestUsedClasses.MomaOrSmsLogin;
import ProgramFiles.GloballyUsedClasses.ComboBoxBuilder;
import ProgramFiles.GloballyUsedClasses.DeleteShotsDirIfExists;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;


// IS FIRED FROM COMBO_STUFF CLASS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class LoginIntoDcs {

	//To be used later on in Extent Report for a test name
	public static String classNameForReport = "-classNameForReport-";	
	
	public static String loginPath = "";
	
	public static int connectionStatus;
	
	static String screenShotsPath = System.getProperty("user.dir") + "\\screenShots\\";
	
	
	public static <E> void openBrowser() throws InterruptedException, AWTException {
		
		System.out.println("<<<<<<<<<<<<<<< RUN BROWSER >>>>>>>>>>>>>>>");
				
		loginPath = TestDataStorage.LOGIN_URL_;
		
		//Runs NetTraffic control to get current net rate
		NetTrafficControl.CheckTraffic.CheckTrafficRun();
		WaitAndNotify.StartWait("Net Traffic Control");
		
		WebDriver webDriver = WebDriverConfiguration.webDriver;
//		
		try { 
						
			// Deletes a folder with former screenshots and recreates an empty folder
			File ScreenshotsDir = new File(screenShotsPath);
			DeleteShotsDirIfExists.DeleteDirIfExists (ScreenshotsDir);
			WaitAndNotify.StartWait("DeleteDirIfExists");
			
			Actions newAction = new Actions(webDriver);
			
	        webDriver.manage().window().maximize();
	        webDriver.manage().deleteAllCookies();
	        
	        //Here a selected path opens in the browser window
			try {
				
				webDriver.navigate().to(loginPath);
				
			} catch (WebDriverException e1) {

				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
				
				try {
					
					webDriver.navigate().to(loginPath);
					TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
					
				} catch (WebDriverException e2) {
					
					ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e2, "Failed to reach the login page. Shut down.");
					
				}//try
				
			}//try
						
			//Credentials:			
			TestDataStorage.TestKeywords();		//Runs a method to obtain test keywords:
			
			Map<String,String> loginCredentials = new LinkedHashMap<>();
			
			loginCredentials.put("txtUser","Sergeyr");
			
			// password setting
			String selectedPassword = "";
			
			selectedPassword = loginPath.contains("qa2.") | loginPath.contains("qa.") ? TestDataStorage.PASS_QA2_ : TestDataStorage.PASS_QA1_;

			// adds password to list
			loginCredentials.put("txtPassword", selectedPassword);
						
			System.out.println("Password map: " + loginCredentials);
			// sends credentials	
			for (Map.Entry<String, String> mapEntry : loginCredentials.entrySet()) {
				
				new WebDriverWait(webDriver, 600)
		        .until(ExpectedConditions.elementToBeClickable(By.id(mapEntry.getKey())));
				newAction.moveToElement(webDriver.findElement(By.id(mapEntry.getKey()))).click().perform();
				
				newAction.sendKeys(mapEntry.getValue());
								
			};//for
			
			//Waits for the Sign in button
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableById(TestDataStorage.SIGNIN_BUTTON_, 120);
			WaitAndNotify.StartWait("Open Browser > Waits for the Sign in button");	
			
			// clicks the button
			newAction.moveToElement(webDriver.findElement(By.id(TestDataStorage.SIGNIN_BUTTON_))).click().perform();
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.05 ));
						
			//OTC verifying flow
			MomaOrSmsLogin.MomaOrSms();
			WaitAndNotify.StartWait("Run the browser > OTC verifying flow");
							
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
			
			//Verify the expected page is open
			System.out.println(webDriver.getCurrentUrl().toString());	

			int ct0 = 0;
			
			String nmbSuffix[] = {"-st","-nd","-rd"};
			
				for (int ct2 = 0; ct2 < 3; ct2++) {
					
					try {
						
						TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
						System.out.println((ct0+1) + nmbSuffix[ct2] + " attempt to load the page.");
						
					} catch (TimeoutException e) {
						
						ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Failure on window\'s loading.");
						
						if (ct0 == 3) {
							
						ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, (ct0+1)+" attempts failed, shut down.");
						
					}//try
						
				};//for
				
				ct0++;
				
			}//for	

		} catch (TimeoutException | NoSuchElementException | NullPointerException e) {
			
			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Because of \"+ e +\" cannot open the page, attempt in \" \r\n"
					+ ComboBoxBuilder.selectedBrowser.toString() + ". The system was shut down.");
			
		} // try

		// releases wait
		WaitAndNotify.Notify("Run browser");

		System.gc();
		
	}	// EOF

}//class


//Typing credentials via Robot
//Set<String> keySet = loginCredentials.keySet();
//String[] keyArray = keySet.toArray(new String[keySet.size()]);
//
//for (int ct0 = 0; ct0 < loginCredentials.size(); ct0++) {
//new WebDriverWait(webDriver, 600)
//.until(ExpectedConditions.elementToBeClickable(By.id(keyArray[ct0].toString())));
//newAction.moveToElement(webDriver.findElement(By.id(keyArray[ct0].toString()))).click().perform();
//
//
//for (char c : loginCredentials.get(keyArray[ct0]).toCharArray()) {
//				    	
//    if (Character.isUpperCase(c) && !Character.isDigit(c)) {
//        robot.keyPress(KeyEvent.VK_SHIFT);
//    }else {
//        robot.keyRelease(KeyEvent.VK_SHIFT);
//    }
//    int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
//    if (KeyEvent.CHAR_UNDEFINED == keyCode) {
//
//    	throw new RuntimeException(
//            "Key code not found for character '" + c + "'");
//    }//if
//    robot.keyPress(keyCode);
//    robot.delay(20);
//    robot.keyRelease(keyCode);
//    robot.delay(20);
//}//for
//};//for