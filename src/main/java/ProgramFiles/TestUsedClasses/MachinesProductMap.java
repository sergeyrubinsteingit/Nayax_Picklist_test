package ProgramFiles.TestUsedClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import ProgramFiles.GloballyUsedClasses.OpenSelectedScreen;
import ProgramFiles.GloballyUsedClasses.RunTestNG;
import ProgramFiles.GloballyUsedClasses.TestDataStorage;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;
import ProgramFiles.GloballyUsedClasses.DrawBorderAroundElement;
import ProgramFiles.GloballyUsedClasses.ExtentRepTestReports;
import ProgramFiles.GloballyUsedClasses.NetTrafficControl;
import ProgramFiles.GloballyUsedClasses.NotificationAlertChecking;
import ProgramFiles.GloballyUsedClasses.PdfReader;

public class MachinesProductMap {
	
	static Actions actions_ = new Actions(WebDriverConfiguration.webDriver);
	static boolean awaitTestFinish = OpenSelectedScreen.awaitTestFinish = true;
	static Object objectForSynch = OpenSelectedScreen.objectForSynch = new Object();
	static WebElement stockTab = null;
	static WebElement conf_ok_btn = null;
	static WebElement fullEmptyEntry = null;
	static JavascriptExecutor javascriptExecutor = (JavascriptExecutor)WebDriverConfiguration.webDriver;
	public static boolean statusFlag;
	static List <WebElement> redEmptyDivs = new ArrayList<WebElement>();
	static List <WebElement> greenEmptyDivs =  new ArrayList<WebElement>();
	static ArrayList <WebElement> allEmptyDivs = new ArrayList<WebElement>();
	static WebDriverWait webDriverWait = new WebDriverWait(WebDriverConfiguration.webDriver, 100);
	public static List<WebElement> menu_labels;
	static String classNameList [] = {"on_hand_container red_bg","on_hand_container green_bg","on_hand_container red_bg"};			
	static ExtentRepTestReports extentRepTestReports = new ExtentRepTestReports();
	public static int totalRows = 0;
	public static int rowsHavingPicks = 0;
	public static boolean allSelected = false;

	public static void productMapTests() throws InterruptedException, IOException, ParserConfigurationException, SAXException, TransformerException {
		
		System.out.println("<<<<<<<<<<<<<< PRODUCT MAP TESTS BEGINS >>>>>>>>>>>>>>");
		
		int twoRepetitionsCounter = 0;
		
		String checkboxSelector;
		
		for (int i = 0; i < 2; i++) {			
	
			// is the box checked?			
			if (twoRepetitionsCounter == 0) {
				
				checkboxSelector = null;
				
			} else {
				
				checkboxSelector = "//input[contains(@id, 'with_all_selection')]";
				
			} // if
			
			// to click on Generate Pick List entry
			selectInStockDropMenu("//label[contains(text(),'Generate Pick List')]");
			WaitAndNotify.StartWait("Machines > Select in drop down menu");
			
			// generate pick list handler
			dialogWindowsHandling("//div[contains(@id, 'generate_picklist_win')]",
					"//button[contains(text(),'Generate Picklist')]", null, "Picklist dialog");
			WaitAndNotify.StartWait("Machines > Generate picklist window handler");
			
			// Confirm dialog handler (generate pick list)
			dialogWindowsHandling("//p[contains(@class, 'conf_msg')]", "//button[contains(@id,'conf_ok_btn')]", null,
					"Confirm dialog");
			WaitAndNotify.StartWait("Machines > Confirm window handler");
			
			// checks notification popup
			NotificationAlertChecking.NotificationAlertCheck();
			WaitAndNotify.StartWait("Product Map > Notification Alert Check");
			
			// to click on Download Pick List entry
			selectInStockDropMenu("//label[contains(text(),'Download Pick List')]");
			WaitAndNotify.StartWait("Machines > Select in drop down menu");
			
			// Download picklist dialog handler
			dialogWindowsHandling("//div[contains(@id, 'print_picklist_win')]", "//button[contains(text(),'Download')]", checkboxSelector,
					"Download picklist dialog");			
			WaitAndNotify.StartWait("Machines > Confirm window handler");
						
			// shifts on the counter
			twoRepetitionsCounter += 1;
			
			// Counts rows in Product Map
			ProductMapRowCounter();
			WaitAndNotify.StartWait("Machines > Row counter in Product Map");
			
			// starts pdf reader
			ProgramFiles.GloballyUsedClasses.PdfReader.ReadPdf(ProgramFiles.GloballyUsedClasses.MostRecentFileInDirectory.selectedFile.getAbsoluteFile().toString(), allSelected);
			WaitAndNotify.StartWait("Machines > PDF reader");	
			
			// Displaying a message
			ProgramFiles.GloballyUsedClasses.InsertDomElementIntoHtmlCode.InsertDivWithMessage(
					com.Start.TestProcedure.elementStateForTestReports, 
					PdfReader.finalMessage, 
					WebDriverConfiguration.webDriver.findElement(By.tagName("body")));
			WaitAndNotify.StartWait("Machine Product Map > PDF Reader");			
			
			// refreshes the page
			WebDriverConfiguration.webDriver.navigate().refresh();
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
			
		}// for				
		
		// releases
		WaitAndNotify.Notify("Machines > Select in drop down menu");
		
	}// TestSets
	
	
	static WebElement selectInStockDropMenu (String selector_) throws InterruptedException {
		
		// searches for 'General' tab to ensure loading of the page after refresh
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath("//*[text()='General']", 300);
		WaitAndNotify.StartWait("Machines > Presence Of the General tab");
				
		// Writes Machine screen tab names into list
		MachinesListOfTabs.CreateListOfMachineTabs();
		WaitAndNotify.StartWait("Machines > Create List Of Machine Tabs");
		
		//Click Products tab
		boolean staleElementState = true;
		
		int staleElementCounter = 0;		
			
			try {
					while (staleElementState) {
						
						for ( int i = MachinesListOfTabs.startIndex; i < MachinesListOfTabs.MachinePageTabs.size(); i++ ) {
						
							if (MachinesListOfTabs.MachinePageTabs.get(i).getText().contains("Products Map")) actions_.moveToElement(MachinesListOfTabs.MachinePageTabs.get(i)).click().perform();
						
						};//for

						staleElementState = false;

					} // while
									
			} catch (StaleElementReferenceException e) {
				
				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));

				staleElementCounter += 1;
				
				staleElementState = true;
				
				if (staleElementCounter > 5) {
			    	
					System.out.println(" <<<<<<<<< Failed to find a tested element. Test is shut down. >>>>>>>>>> ");
					WebDriverConfiguration.webDriver.quit();
			    	System.exit(-1);
			    	
			    };//if
			}				
		
		// searches for 'Stock' tab
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath("//*[text()='Stock']", 120);
		WaitAndNotify.StartWait("Machines > Presence Of the Stock tab");
		
		// Move to the Stock tab
		actions_.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).build().perform();
		
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.5 ));
				
		// searches for the Stock drop menu
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath("//div[contains(@class,'k-animation-container')]", 30);
		WaitAndNotify.StartWait("Machines > Presence Of drop menu'");

		// searches for 'Generate Pick list' entry in the dropdown
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath(selector_, 120);
		WaitAndNotify.StartWait("Machines > Presence of 'Generate Pick List' entry");
		
		// Clicks Generate Pick list entry in drop down
		actions_.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).click().perform();
		
		WaitAndNotify.Notify("Machines > Select in drop down menu");
		
		return ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement;
		
	} // stockMenuOperations
	
	
	static WebElement dialogWindowsHandling (String window_selector, String button_selector, String isCheckedAllSelectionsBox, String window_definition) throws InterruptedException {
		
		// list
		List<String> selectors_ = new ArrayList<String>();
		selectors_.add(window_selector);
		selectors_.add(button_selector);
		
		for (int i = 0; i < selectors_.size(); i++) {
			
			// checking the box if necessary
			if (isCheckedAllSelectionsBox != null) {
				
				// searches for the button in the dialog window
				ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath(isCheckedAllSelectionsBox, 100);
				WaitAndNotify.StartWait("Machines > Presence of the '" + isCheckedAllSelectionsBox + "' box");
				
				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));
				
				if (!ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.isSelected()) {
					
					// Checks the box
					actions_.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).click().perform();
					
					allSelected = true;
					
				}//if
				
			} //if
			
			// searches for the button in the dialog window
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath(selectors_.get(i), 100);
			WaitAndNotify.StartWait("Machines > Presence of the '" + window_definition + "' button");
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));
			
			// Clicks the button
			actions_.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).click().perform();
						
		} // for
		
		// clears the list
		selectors_.clear();
		
		WaitAndNotify.Notify("Machines > Dialog window handler");
		
		return ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement;
		
	} // dialogWindowsHandling
	
	
	static WebElement ProductMapRowCounter () throws IOException, InterruptedException {
		
		// setting to 0 before counting
		totalRows = rowsHavingPicks = 0;
		
		// List to store the table rows
		List<WebElement> productMapRows = new ArrayList<WebElement>();
		
		// searches for the Product Map table
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath(TestDataStorage.PRODUCT_MAP_TABLE_, 100);
		WaitAndNotify.StartWait("Machines > Search for Product Map containing table");
		
		productMapRows = ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.findElements(By.tagName("tr"));
		
		for (int i = 0; i < productMapRows.size(); i++) {
			
			List<WebElement> tdElements = productMapRows.get(i).findElements(By.tagName("td"));
			
			totalRows += 1;
			
			// td containing "missing" value has an index of 18
			if (!tdElements.get(18).getText().trim().equals("0")) rowsHavingPicks += 1;
						
		}; // for
		
		System.out.println("\n====== Total rows: " + totalRows + " ====== Total rows having a pick: " + rowsHavingPicks + " ======");
		
		//to ensure a new file has a time to be processed
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.5 ));
		
		// searches for the most recent file
		ProgramFiles.GloballyUsedClasses.MostRecentFileInDirectory.MostRecentFile();
		WaitAndNotify.StartWait("Machines > Most recent file");		
		
		//releases
		WaitAndNotify.Notify("Row counter in Product Map");
		
		return ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement;
 
	}// ProductMapRowCounter
	
} // end of class
	
	
	
	
	
	
	
	
	
	
//	//Confirms the choice by clicking OK button
//	static void ClickOKbutton (String infoString) throws InterruptedException {
//
//		//Clicks conf_ok_btn button
//		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableByClassNameId("k-widget","k-window-content","conf_ok_btn", 120);
//		WaitAndNotify.StartWait("Machines > Presence Of Element Located By Link Text");
//		
//		if (ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.isDisplayed()) {
//			
////			webDriver.manage().timeouts().wait((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
//			
//			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.15 ));
//			
//			javascriptExecutor.executeScript("arguments[0].click();", ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement);
//			
//			System.out.println(" <<<<<<<<<<<<<<<<< Clicking OK button for >>>>>>>>>>>>>>>>>n " + infoString);
//			
//			WaitAndNotify.Notify("MACHINES: ClickOKbutton");
//			
//		} else {
//			
//			System.out.println(" No dialog window to interact with. ");
//			
//			actions_.moveToElement(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement).build().perform();
//			
//		} //if
//		
//		WaitAndNotify.Notify("MACHINES > ClickOKbutton");
//			
//	};//ClickOKbutton
//	
//	
//	//Makes list of colored divs on screen
//	static List<WebElement> AllDivsList() throws InterruptedException {
//		
//		System.out.println(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  ALL DIVS LIST  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
//		
//		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
//
//		//Clears the list to avoid over-population of it
//		allEmptyDivs.clear();
//		
//		//Draws border around the element					
//		redEmptyDivs = LoginIntoDcs.webDriver.findElements(By.cssSelector("div.on_hand_container.red_bg"));
//		greenEmptyDivs = LoginIntoDcs.webDriver.findElements(By.cssSelector("div.on_hand_container.green_bg"));
//		
//		allEmptyDivs.addAll(redEmptyDivs);
//		allEmptyDivs.addAll(greenEmptyDivs);
//		
//		redEmptyDivs.clear();
//		greenEmptyDivs.clear();
//		
//		System.out.println(" \n<<<<<<<<<<  redEmptyDivs.size() =  "+redEmptyDivs.size()+",  allEmptyDivs.size() =  "+allEmptyDivs.size()+"  >>>>>>>>>>\n ");
//		
//		WaitAndNotify.Notify("ALL DIVS LIST");
//		
//		return allEmptyDivs;
//		
//	};//AllDivsList
//	
//
//	//Selects and draws border around an object
//	static void elementValidation (String classNameString, int entryIndex) throws IOException, InterruptedException {
//		
//		System.out.println(" \n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  ELEMENT VALIDATION  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
//
//		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
//		
//		for(int ii = 0; ii < allEmptyDivs.size(); ii++) {
//			
//			WebElement pickDiv = allEmptyDivs.get(ii);
//			
//			//Repeat attempts if an element is stale
//			StaleElementIssue.WaitForStaleElement(1, pickDiv, null, classNameString);
//			WaitAndNotify.StartWait("Machines > element Validation");
//			
//			//Gets a class name for use in Extent Report
//			LoginIntoDcs.classNameForReport = menu_labels.get(entryIndex).getText().toUpperCase();
//
//			//Fires Extent Report
//			extentRepTestReports.passedORfailed(ii+1);
//			
//		};//for
//		
//		//Run TESTNG
//		RunTestNG.runTestNg();
//		WaitAndNotify.StartWait("MACHINES: RunTestNG");
//		
//		allEmptyDivs.clear();
//		WaitAndNotify.Notify("MACHINES: elementValidation");
//		
//	}//elementValidation
//				
//}////
