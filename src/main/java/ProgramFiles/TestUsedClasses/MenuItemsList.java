package ProgramFiles.TestUsedClasses;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;

public class MenuItemsList {
	
	public static String [] screenFeatures = {
			"Operations",
			"k-animation-container",
			"k-group",
			"k-item",
			"k-link",
			"Reports",
			};
		
	static WebDriver webDriver = WebDriverConfiguration.webDriver;
	public static List<WebElement> menuItems = new ArrayList<>();
	public static List<String> menuItemsText = new ArrayList<>();
	
	
	public static class ItemsList {
				
		public static void CreateItemsList() {
			
			System.out.println("\n============> MenuItemsList > CreateItemsList() is running <================\n");
			
			// Gets menu list
			try {
				
				//Clears the arrays of a data remained from the previous session
				if (menuItems.size()>0) {menuItems.clear();}//if
				if (menuItemsText.size()>0) {menuItemsText.clear();}//if
				
				//Checks if dropdown is opened
				ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.VisibilityOfElementLocatedByClassName(screenFeatures[1], 120);
				WaitAndNotify.StartWait("Open selected screen > Presence Of Element Located By Link Text");	
				
				menuItems = ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement
						.findElements(By.className(screenFeatures[2])
						.className(screenFeatures[3])
						.className(screenFeatures[4])
						.tagName("a")
						);//By.className
				
				for (WebElement menuItem_ : menuItems) {
					
					String menuItemTxt_ = menuItem_.getText();

					if (!menuItemTxt_.contains("v2") && menuItemTxt_ != "") { // Avoid Cortex entry in the dropdown
						
						menuItemsText.add(menuItemTxt_);
						
					};//if
					
				}//for
				
				System.gc();// Garbage collector
				
				System.out.println("\n<<<<<<<<<<<<<<<<<< End of MenuItemsList > CreateItemsList() >>>>>>>>>>>>>>>>>\n");

				WaitAndNotify.Notify("Menu Items List > End of class");
				
			} catch (TimeoutException e) {
				e.printStackTrace();
				webDriver.quit();
				System.exit(-1);
			}//try
		}
	}//MenuItem

}
