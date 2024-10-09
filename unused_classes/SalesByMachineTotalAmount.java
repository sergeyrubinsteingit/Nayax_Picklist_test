package ProgramFiles.TestUsedClasses;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;


public class SalesByMachineTotalAmount {
	
//	public static WebElement TotalInSalesSummary;
	public static double TotalInSalesSummary = 0.0;
	
	
	static void ClickSalesByMachineTab () {
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<< CLICK SALES BY MACHINE TAB BEGINS >>>>>>>>>>>>>>>>>>>>>");
		
		//Search for the tab to click
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableById("sales_by_machine_tab", 120);
//		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableByXpath("//*[ contains(text(),'Sales By Machine') ]", 120);
		WaitAndNotify.StartWait("Click Sales By Machine Tab > Search for the \"Sales By Machine\" text on tab");
		
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
		
		//Releases a wait in Dashboard test
		WaitAndNotify.Notify("Click sales by machine tab");
		
//		try {
//			TotalInSalesSummary();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
	}//ClickSalesByMachineTab
	
	
	public static double TotalInSalesSummary() throws InterruptedException {
		
		System.out.println("\n============> TOTAL IN SALES SUMMARY BEGINS <================\n");
		
				
//		TimeUnit.SECONDS.sleep((int) Math.round(ProgramFiles.GloballyUsedClasses.NetTrafficControl.rateToInterval * 1.0 ));
		
		// Waits for a grid to appear
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath("//*[@id=\"sales_by_machine_tab\"]/div/div[3]", 300);
		WaitAndNotify.StartWait("Total In Sales Summary > Waits for a grid to appear");

		List<WebElement> KendoTotal = WebDriverConfiguration.webDriver.findElements(By.xpath("//td[@role = 'gridcell']"));
		
		System.out.println("<<<KendoTotal size: = "+KendoTotal.size()+" >>>\n");
		
		TotalInSalesSummary = Double.parseDouble(KendoTotal.get(KendoTotal.size()-1).getText());
		
		System.out.println("\n<<<<<<<<<<  TotalInSalesSummary is "+TotalInSalesSummary+" >>>>>>>>>>\n");
		
		//Releases a wait in Dashboard test
		WaitAndNotify.Notify("Total in sales summary");
		
		return TotalInSalesSummary;
		
	}//ClickSalesByMachineTab

}

//GOOGLE DOWNLOADS: chrome://downloads/
