package ProgramFiles.GloballyUsedClasses;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import ProgramFiles.TestUsedClasses.MenuItemsList;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class NavigationBarLinks {
	
	public static WebDriver webDriver = WebDriverConfiguration.webDriver;	
	public static String[] screenFeatures = MenuItemsList.screenFeatures;
	
	
	public static void OpenDropdown(int featureIdx) {
		
		System.out.println("\n<<<<<<<<<<<<<<<<< "+screenFeatures[featureIdx].toUpperCase()+" MENU >>>>>>>>>>>>>>>>>\n");

		try {	
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));
			
			//Clicks Navigation bar item
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableByXpath("//*[text()='"+screenFeatures[featureIdx]+"']", 120);
			WaitAndNotify.StartWait("Navigation Bar Links > OpenDropdown");	
			
			//Click on Operation link in the navigation bar
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));

		} catch (NullPointerException ioe_nulle) {
			
			System.out.println("NullPointerException: " + screenFeatures[0]);
			ioe_nulle.printStackTrace();
			webDriver.quit();
			System.exit(-1);
			
		} catch ( TimeoutException | NoSuchElementException e) {
			
			System.out.println("NoSuchElementException/TimeoutException: " + screenFeatures[0]);
			e.printStackTrace();
			webDriver.quit();
			System.exit(-1);
			
		} catch (InterruptedException e) {

			e.printStackTrace();
			
		}//try
		
		WaitAndNotify.Notify("Operation menu > end");
		
		System.out.println("\n============> End of the Operations menu class <================\n");
		
		System.gc();// Garbage collector
		
	}//openOperationsMenu

}//class
