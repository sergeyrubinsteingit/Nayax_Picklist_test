package ProgramFiles.TestUsedClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ProgramFiles.GloballyUsedClasses.NetTrafficControl;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;


public class MachinesListOfTabs {
	
	public static int startIndex = 0;
	public static List<WebElement> MachinePageTabs = new ArrayList<WebElement>();
		
	
	public static void CreateListOfMachineTabs () throws InterruptedException {
		
		System.out.println("<<<<<<<<<<<<<<<<<< Create List Of Machine Tabs begins >>>>>>>>>>>>>>>>>>");
		
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.2 ));
		
		// empties the list from previously stored data
		if (MachinePageTabs.size() > 0) MachinePageTabs.clear();
		
		// fills the list
		MachinePageTabs = WebDriverConfiguration.webDriver.findElements(By.className("k-link"));
		
		// Removes element with no text
		RemoveNoTextElements (MachinePageTabs);
		ProgramFiles.GloballyUsedClasses.WaitAndNotify.StartWait("Remove No Text Elements");
		
		for (int i1 = 0; i1 < MachinePageTabs.size(); i1++) {
			
			if (MachinePageTabs.get(i1).getText().contains("Dashboard")) {
				
				// Index of the first tab in Machine's options
				startIndex = i1;// k_link.indexOf(k_link_text );
				
			} //if
			
		} //for

		System.out.println(" startIndex :::>> " + startIndex);
		
		ProgramFiles.GloballyUsedClasses.WaitAndNotify.Notify("List Of Machine Tabs > Creates List Of Machine Tabs");
		
		System.out.println(" =========== Create List Of Machine Tabs ends ========== ");
		
	};
	
	
	public static Object RemoveNoTextElements (List<WebElement> MachinePageTabs______) {
		
		System.out.println(" =========== Tabs: Remove No Text Elements begins ========== ");
		
		// Removes element with no text
		for (int i1 = 0; i1 < MachinePageTabs.size(); i1++) {
									
			if (MachinePageTabs.get(i1).getText().length() == 0) {
				MachinePageTabs.remove(MachinePageTabs.get(i1));
				i1--;
			}//if
			
		};//for
		
		// Prints tabs' array
		for (WebElement Tab_ : MachinePageTabs) {
			
			System.out.println("<<<<< MachinePageTabs: " + MachinePageTabs.indexOf(Tab_) + ". " + Tab_.getText() + " >>>>>");
			
		};//for
		
		ProgramFiles.GloballyUsedClasses.WaitAndNotify.Notify("List Of Machine Tabs > Remove No Text Elements");
		
		System.out.println(" =========== Tabs: Remove No Text Elements ends ========== ");
		
		return MachinePageTabs;
		
	}//RemoveNoTextElements

}
