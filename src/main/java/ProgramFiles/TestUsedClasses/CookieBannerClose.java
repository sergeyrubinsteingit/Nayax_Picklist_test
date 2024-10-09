package ProgramFiles.TestUsedClasses;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;

import ProgramFiles.GloballyUsedClasses.NetTrafficControl;
import ProgramFiles.GloballyUsedClasses.RunTestNG;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;

public class CookieBannerClose {
	
	static Actions actions = new Actions(WebDriverConfiguration.webDriver);
	
	public static void CloseCookieBanner () throws InterruptedException {
		
		System.out.println("<<<<<<<<<<<< Close Cookie banner begins >>>>>>>>>");
		
		// Deletes all cookies
		Set<org.openqa.selenium.Cookie> cookies = WebDriverConfiguration.webDriver.manage().getCookies();
		cookies.clear();
		
		// Validates the banner is on the screen
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("onetrust-banner-sdk", 120);
		ProgramFiles.GloballyUsedClasses.WaitAndNotify.StartWait("Close Cookie Banner > Validates the banner is on the screen");
		
		if (ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getSize() != null) {
			
			// draws a border around element
			ProgramFiles.GloballyUsedClasses.DrawBorderAroundElement.DrawBorder(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement, com.Start.TestProcedure.elementStateForTestReports = true);
			ProgramFiles.GloballyUsedClasses.WaitAndNotify.StartWait("Cookie banner");	
			
			// takes a screen shot
			ProgramFiles.GloballyUsedClasses.ScreenShotTaker.ScreenShots("Cookie banner", "CookieBanner", "LoginFlow", "/html/body", com.Start.TestProcedure.elementStateForTestReports);
				
			// Run TEST NG for the record in Extent Report
			com.Start.TestProcedure.screenName = "Cookie banner";
			RunTestNG.TestNgRun();
			WaitAndNotify.StartWait("Cookie banner: Run Test NG");
			
			// Clicks "Cookies" button on the left
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("onetrust-accept-btn-handler", 120);
			ProgramFiles.GloballyUsedClasses.WaitAndNotify.StartWait("Close Cookie Banner > Clicks \"Accept All Cookies\" button");
			
			actions.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).click().perform();
			
			// Clicks "Reject All" button in the dialog window
//			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath("//*[@id=\"onetrust-pc-sdk\"]/div[3]/div[1]/button[1]", 120);
//			ProgramFiles.GloballyUsedClasses.WaitAndNotify.StartWait("Close Cookie Banner > Clicks \"Reject All\" button");
//			
//			actions.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).click().perform();
			
		};//if
		
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
		
		System.out.println("<<<<<<<<<<<< Close Cookie banner ends >>>>>>>>>");
		
		ProgramFiles.GloballyUsedClasses.WaitAndNotify.Notify("Close Cookie Banner");
		
	}; // Close Cookie Banner

} // CookieBannerClose
