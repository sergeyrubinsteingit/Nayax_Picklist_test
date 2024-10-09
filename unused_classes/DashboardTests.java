package ProgramFiles.TestUsedClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ProgramFiles.GloballyUsedClasses.OpenSelectedScreen;
import ProgramFiles.GloballyUsedClasses.NavigationBarLinks;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;

public class DashboardTests {
	
	static WebDriver webDriver = WebDriverConfiguration.webDriver;
	public static Actions actions = new Actions(webDriver);
	
	
	public static void SalesSummaryLastSales() throws InterruptedException {
		
		// Opens report dropdown
//		OpenDropdown(5, "12 months test");
		
		String CallingMethod = "12 months test";
		int idx_ = 5;
		
		// Opens a dropdown:
		NavigationBarLinks.OpenDropdown(idx_);
		WaitAndNotify.StartWait(CallingMethod + " > Operation Menu call");
		
		// Creates an array of dropdown links:
		MenuItemsList.ItemsList.CreateItemsList();
		WaitAndNotify.StartWait(CallingMethod + " > Menu Items List call");

		// Opens selected link from the dropdown:
		OpenSelectedScreen.OpenScreen(MenuItemsList.menuItemsText, 1, "Sales Summary");
		WaitAndNotify.StartWait(CallingMethod + " > Open Selected Screen call");
		
		// Searches by actor
		SalesSummaryTotalTransactionAmount.SearchForTestMachine();
		WaitAndNotify.StartWait(CallingMethod + " > Searches by actor call");
		
		// Opens Sales by Machine screen
		SalesByMachineTotalAmount.ClickSalesByMachineTab();
		WaitAndNotify.StartWait(CallingMethod + " > Opens Sales by Machine screen in Sales Summary");
		
		// Gets total sales per machine in Sales Summary
		SalesByMachineTotalAmount.TotalInSalesSummary();
		WaitAndNotify.StartWait(CallingMethod + " > Gets total sales per machine in Sales Summary");
		
		// Starts Last12monthsVsPrevious12months testing
		MachinesLast12monthsVsPrevious12monthsChart.Last12monthsVsPrevious12months();
		WaitAndNotify.StartWait(CallingMethod + " > Last 12 months Vs Previous 12 months chart test");
		
		WaitAndNotify.Notify("Sales Summary Last Sales");
		
	}//SalesSummaryLastSales
	
	
	
//	public static void Last12monthsVsPrevious12months() throws InterruptedException {		
//		
//		System.out.println(" =========== Last 12 months Vs Previous 12 months test begins ========== ");
//		
//		//Method to find Sales Summary's Last Sales value for comparison
//		SalesSummaryLastSales();
//		WaitAndNotify.StartWait("Last 12 months > Sales Summary Last Sales");
//				
//		System.out.println(" =========== Last 12 months Vs Previous 12 months test ends ========== ");
//		
//	}// Last12monthsVsPrevious12months
	

//	
//	public static void OpenDropdown (int idx_, String CallingMethod) throws InterruptedException {
//		
//		// Opens a dropdown:
//		NavigationBarLinks.OpenDropdown(idx_);
//		WaitAndNotify.StartWait(CallingMethod + " > Operation Menu call");
//		
//		// Creates an array of dropdown links:
//		MenuItemsList.ItemsList.CreateItemsList();
//		WaitAndNotify.StartWait(CallingMethod + " > Menu Items List call");
//
//		// Opens selected link from the dropdown:
//		OpenSelectedScreen.OpenScreen(MenuItemsList.menuItemsText, 1, "Sales Summary");
//		WaitAndNotify.StartWait(CallingMethod + " > Open Selected Screen call");
//		
//		// Searches by actor
//		SalesSummaryTotalTransactionAmount.SearchForTestMachine();
//		WaitAndNotify.StartWait(CallingMethod + " > Searches by actor call");
//		
//		// Opens Sales by Machine screen
//		SalesByMachineTotalAmount.ClickSalesByMachineTab();
//		WaitAndNotify.StartWait(CallingMethod + " > Opens Sales by Machine screen in Sales Summary");
//		
//		// Gets total sales per machine in Sales Summary
//		SalesByMachineTotalAmount.TotalInSalesSummary();
//		WaitAndNotify.StartWait(CallingMethod + " > Gets total sales per machine in Sales Summary");
//		
//		
//		//temporary call for Machines to control the changes made there. To be deleted when the changes are finished.
////		try {
////			Machines.TestSets();
//////			WaitAndNotify.StartWait(CallingMethod + " > Machines Screen call");
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//	};//OpenDropdown
	
}



