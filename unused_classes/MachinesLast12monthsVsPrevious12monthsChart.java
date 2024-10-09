package ProgramFiles.TestUsedClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;

public class MachinesLast12monthsVsPrevious12monthsChart {

	public static void Last12monthsVsPrevious12months() throws InterruptedException {		
	
	System.out.println("<<<<<<<<<<<<<<<<< Last 12 months Vs Previous 12 months test begins >>>>>>>>>>>>>>>>>");
	
	// Searches for test machine in the hierarchy
	MachinesSearchForTestMachine.SelectTestMachine();
	WaitAndNotify.StartWait("Last 12 months Vs Previous 12 months > Select Test Machine");
	
	// Creates a list of tab names which are on the machine's page
	MachinesListOfTabs.CreateListOfMachineTabs();
	WaitAndNotify.StartWait("Last 12 months Vs Previous 12 months > Creates a list of tab names which are on the machine's page");
	
	// Clicks "Dashboard" tab
	for (WebElement Tabs_ : MachinesListOfTabs.MachinePageTabs) {
		
		if (Tabs_.getText().equals("Dashboard")) {
			
			// Clicks on Dashboard tab
			Actions actions_ = new Actions(WebDriverConfiguration.webDriver);
			actions_.moveToElement(Tabs_).click().perform();
			
		};//if
		
	};//for
		
	// 
	System.out.println(" =========== Last 12 months Vs Previous 12 months test ends ========== ");
	
}// Last12monthsVsPrevious12months
	
}
