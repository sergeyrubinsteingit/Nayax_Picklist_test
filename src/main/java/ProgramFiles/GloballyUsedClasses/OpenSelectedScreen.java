package ProgramFiles.GloballyUsedClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ProgramFiles.TestUsedClasses.MenuItemsList;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;



public class OpenSelectedScreen {
	
	static WebDriver webDriver = WebDriverConfiguration.webDriver;
	public static List<WebElement> menuItems_ = new ArrayList<>();	
	public static List<WebElement> kGroup = new ArrayList<>();	
	public static WebElement itemToClick;
	public static boolean awaitTestFinish = true;	
	public static Object objectForSynch = new Object();	
	public static String menuEntryName = "";
	static String screenShotsPath = System.getProperty("user.dir") + "\\screenShots\\";
	public static WebElement ExpectedElement; //WebElement to interact with


	public static void OpenScreen(List<String> menuItemsText, int ItemIdx, String sideDropItem) {
				
		System.out.println("<<<<<<<<<<<<<<<<<<<<<< OPEN SELECTED SCREEN BEGINS >>>>>>>>>>>>>>>>>>>>>>");
		
		try {
			 
		 	Actions actions = new Actions(WebDriverConfiguration.webDriver); 
			
			for (int c0=0;c0<menuItemsText.size();c0++) {
				
				System.out.println("============> "+ c0+ ". " + menuItemsText.get(c0) + " <================\n");
				
			};//for
			
			//Entry to select in a dropdown
			menuEntryName = menuItemsText.get(ItemIdx);
			
			System.out.println("\n============> menuEntryName is: " + menuEntryName + " <================\n");
			
	        try {
				
				//Opens Reports dropdown item
				ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.VisibilityOfElementLocatedByClassName(MenuItemsList.screenFeatures[1], 120);
				WaitAndNotify.StartWait("Open selected screen > Presence Of Element Located By Class Name");								
				
				//Clicks Reports dropdown item
				ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByLinkText(menuEntryName, 120);
				WaitAndNotify.StartWait("Open selected screen > Presence Of Element Located By Link Text");								
								        
					//Opens selection in new tab
					if (ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getSize() != null) {
						
						//Imitates right-click by mouse, opens side dropdown menu
						if (ItemIdx == 1) {
							
							actions.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).perform();
							
						} else {
							
							actions.keyDown(Keys.LEFT_CONTROL).click(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).keyUp(Keys.LEFT_CONTROL).build().perform();
							
						};//if
						
						if (sideDropItem.length() > 0) {
							
							//Clicks side-drop item, if it exists
							ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByLinkText(sideDropItem, 120);
							WaitAndNotify.StartWait("Open selected screen > Presence Of Element Located By Link Text");								
															
							//Opens in a new tab
							actions.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement)
								.keyDown(Keys.LEFT_CONTROL).click(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement)
								.keyUp(Keys.LEFT_CONTROL).build().perform();
							
						}//if

						TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));
						
					}else{
				        System.out.println("------&&> Right-click drop in menu is unaccessable. The system is shut down.");
						webDriver.quit();
						System.exit(-1);
					}//if

				//Switches to new tab
			   ArrayList<String> tabsList = new ArrayList<String> (webDriver.getWindowHandles());
			   webDriver.switchTo().window(tabsList.get(tabsList.size()-1));	
			   
			   TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));
			   
			   WaitAndNotify.Notify("Open selected screen");
        
	        } catch (Exception e1) {
	        	
				System.out.println("From Open Selected Screen. Web element "
						        + WaitForExpectedCondition.ExpectedElement 
						        +" was not found. \nTimeout expired, the test is shut down");
				
				e1.printStackTrace();
				webDriver.quit();
				System.exit(-1);
			}

		} catch (TimeoutException  | StaleElementReferenceException e) {
		        System.out.println("Open selected screen: FAILED");
				e.printStackTrace();
				webDriver.quit();
				System.exit(-1);
		}//try
	}//main

}




//try {
////Invokes test class
//String classPath = "ProgramFiles.TestClasses.";
//String testClassID = classPath + "TestClass_" + String.valueOf(ItemIdx+1);
//String testMethodID = "TestMethod_" + String.valueOf(ItemIdx+1);
//System.out.println("testClassID > " + testClassID);
//Class<?> selectedClass;
//selectedClass = Class.forName(testClassID);
//System.out.println("------>  "+selectedClass.getPackageName());
//
//Method method = selectedClass.getMethod(testMethodID, String.class);
//
//System.out.println("------%>  "+ method.getName());
//
//method.invoke(null, menuItemsText.get(ItemIdx).toString());
//
////TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));
//
////synchronized (objectForSynch) {
////	while (awaitTestFinish) {
////        try {
////        	objectForSynch.wait();
////        } catch (InterruptedException e) {
////            Thread.currentThread().interrupt(); 
////            System.out.println("Thread Interrupted");
////        }//try
////    }//while
////    awaitTestFinish = true;			        
////};//synchronized
//
//
//System.out.println("\n\n\nOPEN SELECTED SCREEN IS WAITING AFTER STARTING A METHOD *- " + method.getName().toUpperCase() + " -*\n\n\n");
////TimeUnit.MINUTES.sleep(5);
//
////Waiting
//WaitAndNotify.StartWait("OPEN SELECTED SCREEN AFTER STARTING A METHOD *- " + method.getName().toUpperCase());
//
//System.gc();
//
//System.out.println("\n============> OPEN SELECTED SCREEN ENDS <================\n");
//
//} catch ( ClassNotFoundException |  IllegalArgumentException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
//e.printStackTrace();
//webDriver.quit();
//System.exit(-1);
//}
//TestClass_1.main(null);
//catch (InterruptedException e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//}
//}//for
