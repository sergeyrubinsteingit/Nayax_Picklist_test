package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ProgramFiles.NavigationBarLinks;
import ProgramFiles.OpenSelectedScreen;
import ProgramFiles.MenuItemsList;
import ProgramFiles.LoginIntoDcs;
import ProgramFiles.RunTestNG;
import ProgramFiles.WaitAndNotify;
import ProgramFiles.AppendScreenshotsToHTML;
import ProgramFiles.ExtentRepTestReports;
import ProgramFiles.MachinesLisOfTabs;
import ProgramFiles.GloballyUsedClasses.*; 

public class ProductMapOldVersion {

	static WebDriver webDriver = LoginIntoDcs.webDriver;
	static Actions actions_ = new Actions(webDriver);
	static boolean awaitTestFinish = OpenSelectedScreen.awaitTestFinish = true;
	static Object objectForSynch = OpenSelectedScreen.objectForSynch = new Object();
	static WebElement stockTab = null;
	static WebElement conf_ok_btn = null;
	static WebElement fullEmptyEntry = null;
	static JavascriptExecutor javascriptExecutor = (JavascriptExecutor)webDriver;
	public static boolean statusFlag;
	static List <WebElement> redEmptyDivs = new ArrayList<WebElement>();
	static List <WebElement> greenEmptyDivs =  new ArrayList<WebElement>();
	static ArrayList <WebElement> allEmptyDivs = new ArrayList<WebElement>();
	static WebDriverWait webDriverWait = new WebDriverWait(webDriver, 100);
	public static List<WebElement> menu_labels;
	static String classNameList [] = {"on_hand_container red_bg","on_hand_container green_bg","on_hand_container red_bg"};			
	static ExtentRepTestReports extentRepTestReports = new ExtentRepTestReports();
	private static String currentLabel = "";

	public static void TestSets() throws InterruptedException {
		
		System.out.println(" =========== MACHINES ========== ");
		
		String CallingMethod = "Machines";
		int idx_ = 5;
		
		// Opens a dropdown:
		NavigationBarLinks.OpenDropdown(idx_);
		WaitAndNotify.StartWait(CallingMethod + " > Operation Menu call");
		
		// Creates an array of dropdown links:
		MenuItemsList.ItemsList.CreateItemsList();
		WaitAndNotify.StartWait(CallingMethod + " > Menu Items List call");

		// Opens selected link from the dropdown:
//		OpenSelectedScreen.OpenScreen(MenuItemsList.menuItemsText, 1, "Machines");
//		WaitAndNotify.StartWait(CallingMethod + " > Open Selected Screen call");
		
		// Writes Machine screen tab names into list
		MachinesLisOfTabs.CreateListOfMachineTabs();
		WaitAndNotify.StartWait(CallingMethod + " > Create List Of Machine Tabs");
		
		//Click Products
		for ( int i = MachinesLisOfTabs.startIndex; i < MachinesLisOfTabs.MachinePageTabs.size(); i++ ) {
			
			if (MachinesLisOfTabs.MachinePageTabs.get(i).getText().contains("Products Map")) actions_.moveToElement(MachinesLisOfTabs.MachinePageTabs.get(i)).click().perform();
							
		};//for
		
		//Checking Stock tab presence
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath("//*[text()='Stock']", 120);
		WaitAndNotify.StartWait("Machines > Presence Of the Stock tab");
		
		// Move to the Stock tab
		actions_.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).build().perform();
		
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));
		
		//Drop menu entries
		menu_labels = webDriver.findElements(By.className("k-item").className("k-link").tagName("label"));
		
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));
		
		// Removes element with no text
//		MachinesLisOfTabs.RemoveNoTextElements (menu_labels);
//		WaitAndNotify.StartWait("MACHINES > Remove No Text Elements");
		
		try {
			for (int i = 7; i >= 6; i--) {
				
				// Move to the Stock tab
				StaleElementIssue.WaitForStaleElement(4, ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement, null, null);
				
				//Clicks Stock dropdown item
				ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath(" //*[text()='" + menu_labels.get(i).getText() + "'] ", 120);
				WaitAndNotify.StartWait("Machines > Presence Of Element Located By Link Text");
				
				javascriptExecutor.executeScript("arguments[0].click();", ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement);
				
				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));
				
				//Moves a mouse off its position over the dropdown
				actions_.moveByOffset(-33, 33);
				
				System.out.println(" <<<<<<<<<<<<<<<<<< "+ menu_labels.get(i).getText().toUpperCase() +" click was performed >>>>>>>>>>>>>>>>> ");

				// For the Extent REport test label
				currentLabel = menu_labels.get(i).getText().toUpperCase().toString();
				LoginIntoDcs.classNameForReport = currentLabel + " %%%%%%%%%%";
				
				// Confirms the choice by clicking OK button
				ClickOKbutton ("After " + currentLabel + ", var 2");						
				WaitAndNotify.StartWait("MACHINES");				
				
				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));
				
				//Creates a list of web elements to check
				AllDivsList();
				ProgramFiles.WaitAndNotify.StartWait("MACHINES");
	
				//Repeat attempts if an element is stale : Machines > hovers a mouse over Stock tab
				StaleElementIssue.WaitForStaleElement(2, ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement, null, null);
//				WaitAndNotify.StartWait("Machines > hovers a mouse over Stock tab");
				
				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));
				
				//Repeat attempts if an element is stale: Machines > move a mouse out of Stock tab
				StaleElementIssue.WaitForStaleElement(3, ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement, null, null);
//				WaitAndNotify.StartWait("Machines > move a mouse out of Stock tab");
							
				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));

				//Draws border
				int classNameIdx = (i == 7) ? 0 : 1; // class Name Position according to Index in the Stock dropdown
				elementValidation(classNameList[classNameIdx], i);
				ProgramFiles.WaitAndNotify.StartWait("MACHINES: elementValidation");
				
				//Run TESTNG
				RunTestNG.runTestNg();
				WaitAndNotify.StartWait("MACHINES: RunTestNG");
								
				//Screenshot
				ProgramFiles.ScreenShotsMaker.ScreenShots(menu_labels.get(i).getText().toUpperCase().toString(), "Machines", "//*[@id='tab_product_map']/div[1]/div[3]");
				WaitAndNotify.StartWait("MACHINES: ScreenShotsMaker");
			
				System.out.println(" <<<<<<<<<<<<<<<<< Machine was filled up >>>>>>>>>>>>>>>>> ");
				
			}; //for			
			 
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));
			
			//Appends to HTML report a div containing screenshots 
			AppendScreenshotsToHTML.AppendNewDiv("Machines");
			WaitAndNotify.StartWait("MACHINES: AppendScreenshotsToHTML");

		} catch (Exception e1) {
			
			System.out.println(" <<<<<<<<<<<<<<<<< Menu's flow failed. &>>>>>>>>>>>>>>>>> ");
			e1.printStackTrace();
			webDriver.quit();
			System.exit(-1);
			
		}//try

		System.out.println(" <<<<<<<<<<<<<<<<<<<< TEST SETS for MachinesTestCase ENDED >>>>>>>>>>>>>>>>>>>> ");
		WaitAndNotify.NotifyAll("MACHINES AT THE END");
		
	}//TestSets
	
	
//	static Object RemoveNoTextElements (List<WebElement> k_link) {
//		
//		// Removes element with no text
//		for (int i1 = 0; i1 < k_link.size(); i1++) {
//									
//			if (k_link.get(i1).getText().length() == 0) {
//				k_link.remove(k_link.get(i1));
//				i1--;
//			}//if
//			
//		};//for
//		
//		WaitAndNotify.Notify("MACHINES: RemoveNoTextElements");
//		
//		return k_link;
//		
//	}//RemoveNoTextElements
	
	
	//Confirms the choice by clicking OK button
	static void ClickOKbutton (String infoString) throws InterruptedException {

		//Clicks conf_ok_btn button
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableByClassNameId("k-widget","k-window-content","conf_ok_btn", 120);
		WaitAndNotify.StartWait("Machines > Presence Of Element Located By Link Text");
		
		if (ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.isDisplayed()) {
			
//			webDriver.manage().timeouts().wait((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));
			
			javascriptExecutor.executeScript("arguments[0].click();", ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement);
			
			System.out.println(" <<<<<<<<<<<<<<<<< Clicking OK button for >>>>>>>>>>>>>>>>>n " + infoString);
			
			WaitAndNotify.Notify("MACHINES: ClickOKbutton");
			
		} else {
			
			System.out.println(" No dialog window to interact with. ");
			
			actions_.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).build().perform();
			
		} //if
		
		WaitAndNotify.Notify("MACHINES > ClickOKbutton");
			
	};//ClickOKbutton
	
	
	//Makes list of colored divs on screen
	static List<WebElement> AllDivsList() throws InterruptedException {
		
		System.out.println(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  ALL DIVS LIST  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));

		//Clears the list to avoid over-population of it
		allEmptyDivs.clear();
		
		//Draws border around the element					
		redEmptyDivs = LoginIntoDcs.webDriver.findElements(By.cssSelector("div.on_hand_container.red_bg"));
		greenEmptyDivs = LoginIntoDcs.webDriver.findElements(By.cssSelector("div.on_hand_container.green_bg"));
		
		allEmptyDivs.addAll(redEmptyDivs);
		allEmptyDivs.addAll(greenEmptyDivs);
		
		redEmptyDivs.clear();
		greenEmptyDivs.clear();
		
		System.out.println(" \n<<<<<<<<<<  redEmptyDivs.size() =  "+redEmptyDivs.size()+",  allEmptyDivs.size() =  "+allEmptyDivs.size()+"  >>>>>>>>>>\n ");
		
		WaitAndNotify.Notify("ALL DIVS LIST");
		
		return allEmptyDivs;
		
	};//AllDivsList
	

	//Selects and draws border around an object
	static void elementValidation (String classNameString, int entryIndex) throws IOException, InterruptedException {
		
		System.out.println(" \n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  ELEMENT VALIDATION  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");

		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
		
		for(int ii = 0; ii < allEmptyDivs.size(); ii++) {
			
			WebElement pickDiv = allEmptyDivs.get(ii);
			
			//Repeat attempts if an element is stale
			StaleElementIssue.WaitForStaleElement(1, pickDiv, null, classNameString);
			WaitAndNotify.StartWait("Machines > element Validation");
//			boolean staleElementState = true;
//			
//			int counter1 = 0;
			
//			  try{
//				  
//				while(staleElementState){
//				
//					//Draws border around an object
//					if (pickDiv.getAttribute("class").toString().equals(classNameString)) {
//						
//						((JavascriptExecutor)LoginIntoDcs.webDriver).executeScript("arguments[0].style.border='3px solid #1d6e44'", pickDiv);
//						LoginIntoDcs.extentReportFlag = true;
//						
//					} else {
//						
//						((JavascriptExecutor)LoginIntoDcs.webDriver).executeScript("arguments[0].style.border='3px solid #ff3300'", pickDiv);
//						LoginIntoDcs.extentReportFlag = false;
//						
//					};//if						
//
//				//Breaks the While cycle	     
//				staleElementState = false;
//				
//				}//while
//
//			  } catch(StaleElementReferenceException e){
//				  
//					//Continues the While cycle
//				  	staleElementState = true;
//				  	
//				  	TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
//				  	
//				    counter1++;
//				    
//					    if (counter1 > 3) {
//					    	
//							System.out.println(" <<<<<<<<< Failed to find a tested element. Test is shut down. >>>>>>>>>> ");
//					    	webDriver.quit();
//					    	System.exit(-1);
//					    	
//					    };//if
//			  }//try
			
			//Gets a class name for use in Extent Report
			LoginIntoDcs.classNameForReport = menu_labels.get(entryIndex).getText().toUpperCase();

			//Fires Extent Report
			extentRepTestReports.passedORfailed(ii+1);
			
		};//for
		
		//Run TESTNG
		RunTestNG.runTestNg();
		WaitAndNotify.StartWait("MACHINES: RunTestNG");
		
		allEmptyDivs.clear();
		WaitAndNotify.Notify("MACHINES: elementValidation");
		
	}//elementValidation
				
}//////////////////////////////////////////////////***END***/////////////////////////////////////////////////////


//// Opens Operations dropdown:
//NavigationBarLinks.OpenDropdown(0);
//WaitAndNotify.StartWait("Machines test > Operation Menu call");
//
//// Creates an array of Operations dropdown links:
//MenuItemsList.ItemsList.CreateItemsList();
//WaitAndNotify.StartWait("Machines test > Menu Items List call");
//
//// Opens Machines screen from Operations dropdown:
//OpenSelectedScreen.OpenScreen(MenuItemsList.menuItemsText, 0, "");
//WaitAndNotify.StartWait("Machines test > Open Selected Screen call");
//
//// Search for machine:
//ProgramFiles.SelectMachineInHierarchy.SearchForMachine(MenuItemsList.menuItemsText.get(0));
//WaitAndNotify.StartWait("Machines test > SelectMachineInHierarchy.SearchForMachine call");

//TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
//			
//List<WebElement> k_link = webDriver.findElements(By.className("k-link"));
//int startIndex = 0;
//
//// Removes element with no text
//RemoveNoTextElements (k_link);
//WaitAndNotify.StartWait("MACHINES");
//
//for (int i1 = 0; i1 < k_link.size(); i1++) {
//	
//	if (k_link.get(i1).getText().contains("Dashboard")) {
//		
//		// Index of the first tab in Machine's options
//		startIndex = i1;// k_link.indexOf(k_link_text );
//		
//	} //if
//	
//} //for
//
//System.out.println(" startIndex :::>> " + startIndex);
