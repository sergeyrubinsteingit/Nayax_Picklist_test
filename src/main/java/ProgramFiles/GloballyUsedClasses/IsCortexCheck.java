package ProgramFiles.GloballyUsedClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.Color;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;

public class IsCortexCheck {
	
	static String filterBarColor = "";
	//Action instance
	private static Actions Actions_ = new Actions(WebDriverConfiguration.webDriver);

	public static String filterBarColor() {
		
		System.out.println("\n<<<<<<<<<<< Running filterBarColor() >>>>>>>>>>>>\n");
		
		String cssSelector_ = "//*[@id=\"filter_container\"]";
		
		
		try {
//			WebElement filter_container = (WebElement) new FluentWait<WebDriver>(LoginIntoDcs.webDriver)
//					.withTimeout(33, TimeUnit.SECONDS)
//					.pollingEvery(3, TimeUnit.SECONDS)
//					.ignoring(Exception.class)
//					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cssSelector_)));
//			
			//Waits for the icon container
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableByXpath(cssSelector_, 120);
			WaitAndNotify.StartWait("Is Cortex Check > Waits for the icon container");	
			
			filterBarColor = ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getCssValue("background-color");
			String colorToHex = Color.fromString(filterBarColor).asHex();
			
			if(filterBarColor.equalsIgnoreCase("rgba(67, 67, 67, 1)")) {//"3182b7" 
				
				SwitchTheme();
				WaitAndNotify.StartWait("From filterBarColor() to SwitchTheme()");	
				System.out.println("\n<<<<<<<<<<< Background color of the filter bar is " + colorToHex + " >>>>>>>>>>>>\n");
				
			};//if
						
		} catch (Exception e) {
			
			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Failed to discover the model DCS/Cortex. The test is shut down.");
			
		}//try
		
		WaitAndNotify.Notify("Notify from Filter Bar Color");
				
		return filterBarColor;
		
	}//filterBarColor
	
	
	static void SwitchTheme() {
		
		System.out.println("\n<<<<<<<<<<< Running SwitchTheme() >>>>>>>>>>>>\n");

		WebElement html_ = WebDriverConfiguration.webDriver.findElement(By.tagName("html"));
		for (int i = 0; i < 3; i++) {
			
			System.out.println("\n<<<<<<<<<<< SwitchTheme() : Zoom out >>>>>>>>>>>>\n");
//			html_.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			
		};//for
		
		try {
						
			///Zoom out
			JavascriptExecutor js = (JavascriptExecutor) WebDriverConfiguration.webDriver;
			String zooJscript = "document.body.style.zoom='0.94'";  
			js.executeScript(zooJscript);
			
			//Waits for the icon to switch to dcs
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableByXpath("//*[@id=\"menu_new_core_image\"]", 120);
			WaitAndNotify.StartWait("Is Cortex Check > Waits for the icon to switch to dcs");	
			
//			//Click menu_theme_icon			
			((JavascriptExecutor) WebDriverConfiguration.webDriver).executeScript("arguments[0].click();", ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement);
			
			WaitAndNotify.Notify("Notify from Switch Theme");
			
		} catch (MoveTargetOutOfBoundsException  e) {
			
			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Failed to change the color theme. The test is shut down.");
			
		} catch (JavascriptException e) {
			
			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Failed to zoom out due to Javascripr exception. The test is shut down.");
			
		}	//try
		
	}//SwitchTheme
	
}//class
