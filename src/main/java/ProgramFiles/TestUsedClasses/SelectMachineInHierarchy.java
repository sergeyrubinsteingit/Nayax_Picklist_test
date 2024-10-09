package ProgramFiles.TestUsedClasses;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import ProgramFiles.GloballyUsedClasses.*;


public class SelectMachineInHierarchy extends OpenSelectedScreen {
	
	static String selectedActor = "";
	static String screenPath = WebDriverConfiguration.webDriver.getCurrentUrl().toString();	
	static Actions actions = new Actions(WebDriverConfiguration.webDriver);
	static String screenShotsPath = System.getProperty("user.dir") + "\\screenShots\\";
	
	public static String selectedMachineName = "";

	
	public static void SearchForMachine(String menuItemsText) throws InterruptedException {
		
		System.out.println("<<<<<<<<<<<< Search For Machine begins >>>>>>>>>");
				
		//Create in Screenshots directory new sub-directory for this test's screenshots
		File testShotsDir = new File(screenShotsPath + "Machine_test");

		Boolean isDirCreated = testShotsDir.mkdir();
		
			if (!isDirCreated) {
				testShotsDir.delete();
				testShotsDir.mkdir();
			};//if

		try {
			
			// Checking an environment
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
			
			//Runs a method to obtain test keywords:
			TestDataStorage.TestKeywords();
			
			// actor to search by
			selectedActor = screenPath.contains("qa2.") | screenPath.contains("qa.") ? TestDataStorage.ACTOR_QA_ : TestDataStorage.ACTOR_PROD_;
			
			// Filling the Operator input to open a tree
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("actor_id_filter_input", 120);
			WaitAndNotify.StartWait("Macine in hierarchy > Filling the Operator input to open a tree");
			
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();			
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.sendKeys(selectedActor);		
			
			// Select an operator from the drop menu
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByClassName("pickerText", 120);
			WaitAndNotify.StartWait("Macine in hierarchy > Select an operator from the drop menu");			
			
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
			
			// Click on Select button
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("search_machine_btn", 120);
			WaitAndNotify.StartWait("Macine in hierarchy > Click on Select button");	
			
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
			
				// Select Unassigned Area folder; if it's missing pick a machine out of tree
				try {
					
					SelectMachine(menuItemsText);
					WaitAndNotify.Notify("Select machine in hierarchy > SelectMachine(menuItemsText)");
					
				} catch (Exception e) {
					
					System.out.println("<<<<<<<<<<<< Select Machine method couldn't be invoked >>>>>>>>>");
					e.printStackTrace();
					
				}//try
			
		} catch (NoSuchElementException | ElementNotFoundException | TimeoutException | InterruptedException e1) {
			
			WaitAndNotify.Notify("Select machine in hierarchy");
			e1.printStackTrace();
			
		}//try
		
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
		
		System.out.println("<<<<<<<<<<<< Search For Machine ends >>>>>>>>>");
	
	}// SelectMachineInHierarchy
	

	static int count_ = 0;	
	public static boolean exceptionState = true;	
	static String [] machineSelectors = {"//*[text()[contains(., 'Unassigned Area')]]", "//*[text()[(contains(., 'aAlpha-TestMachine'))]]" };
	
	
	private static void SelectMachine(String menuItemName) {
		
		while (exceptionState) {
			
			System.out.println("<<<<<<<<<<<< SelectMachine >>>>>>>>>");
			
			try {
				
				//For a case Unassigned Area is open
				count_ += 1;
				
				findMachineInHierarchy(machineSelectors[1], count_);
				
			} catch (Exception e) {
				
				try {
					
					//For a case Unassigned Area is closed
					for (int i = 0; i < machineSelectors.length; i++) {
						
						count_ += 1;
						
						findMachineInHierarchy(machineSelectors[i], count_);
						
					}; // for
					
					exceptionState = false;
		
				} catch (Exception e2) {
					
					System.out.println("<<<<<<<<<<<< From Select Machine in Hierarchy: Failed to find a machine, the test is shut down. >>>>>>>>>");
					WebDriverConfiguration.webDriver.quit();
					System.exit(-1);
					
				}//try
				
				exceptionState = true;
				
			}//try
			
			exceptionState = false;
			
			WaitAndNotify.Notify("Select Machine");
			
		}// while
	
	}//select machine
	
	
	public static void findMachineInHierarchy(String selector_, int counter_) {
		
		System.out.println("<<<<<<<<<<<< findMachineInHierarchy >>>>>>>>>");
		
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath(selector_, 25);			
			WaitAndNotify.StartWait("Machine in hierarchy > Check If Unassigned Area Is Open, attempt [" + counter_ + "]");

			actions.moveToElement(WaitForExpectedCondition.ExpectedElement).click().perform();
					
	}// findMachineInHierarchy

}// class



//The block is to get a class simple name for further usage
// instance of the class
//TestClass_1 classInstance = new TestClass_1();
//Class<?> thisClass = classInstance.getClass();
//
//// returns the name of the class
//String name = thisClass.getName();
//System.out.println("<<<<<<<<<<<<<<<<<<<<<< Class Name = " + name + ">>>>>>>>>>>>>>>>>>>>>>");
//
//// returns the simple name of the class
//String classSimpleName = thisClass.getSimpleName();
//System.out.println("<<<<<<<<<<<<<<<<<<<<<< Class SimpleName = " + classSimpleName + ">>>>>>>>>>>>>>>>>>>>>>");

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

