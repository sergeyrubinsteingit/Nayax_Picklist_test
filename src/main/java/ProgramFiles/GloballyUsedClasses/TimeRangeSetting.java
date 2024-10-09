package ProgramFiles.GloballyUsedClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleniumhq.jetty9.util.log.Log;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.MoveElementIntoViewport;

public class TimeRangeSetting {
	
	
	public static String TimeIntervalDropdown () throws InterruptedException {
		
		System.out.println("\n============> TIME INTERVAL DROPDOWN BEGINS <================\n");
		
		//Opens Time Interval dropdown
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableByXpath("//*[@id=\"time_period\"]/span/span/span[1]", 120);
		WaitAndNotify.StartWait("Time Interval Field > Search for the \"Time Interval\" field");
				
		//Pause to let a dropdown open
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
		
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
		
		//Retrieves time periods list
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("time_period_select-list", 120);
		WaitAndNotify.StartWait("Time Interval Field > Search for the \"time_period_select-list\" ");
		
		//Creates a list of all the options present in the dropdown
		List<WebElement> ElementsInsideDropdown = ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.findElements(By.xpath(".//*"));

		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));
		
		List<String> OptionsInDropdown = new ArrayList<String>();
		
			for (WebElement DropdownOption : ElementsInsideDropdown) {
				
				if (DropdownOption.getText().length() != 0) {					

					OptionsInDropdown.add(DropdownOption.getText());
					
//					System.out.println("OptionsInDropdown: " + DropdownOption.getText());
					
				};//if
				
			};//for
			
			for (String value : OptionsInDropdown)
			{
				System.out.println("Value of element"+OptionsInDropdown.indexOf(value)+": " + value);
			}
			
			ElementsInsideDropdown.clear();
			
			//temp
			System.out.println("LIST SIZE - 1 = " + (OptionsInDropdown.size()-1));
			
			String TimePeriod = OptionsInDropdown.get(13);
//			String TimePeriod = OptionsInDropdown.get(OptionsInDropdown.size()-2);
		
		//Fires time range setting function
		TimeIntervalSetting(TimePeriod, OptionsInDropdown.size()-2);
		
		return TimePeriod;
				
	};//TimeIntervalField
	
	
	public static void TimeIntervalSetting (String TimePeriod, int ElementIndex) throws InterruptedException {
		
		System.out.println("\n============> TIME INTERVAL SETTINGS BEGIN <================\n");
		
		//Retrieves Date Range option
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath("//*[@id=\"time_period_select_listbox\"]/li["+ElementIndex+"]", 120);
//		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByXpath("//*[contains(text(),'"+TimePeriod+"')]", 120);
		WaitAndNotify.StartWait("Time Interval Settings > Search for the \"Date Range\" option");	

		//Scrolls down to Time Range option
		MoveElementIntoViewport.MoveIntoViewport(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement);
		WaitAndNotify.StartWait("Time Interval Settings > Fires \"Move Into Viewport\"");
		
		//Clicks Date Range option
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
		
		//In case it is a search for time range
		IfTimeRange();
		
	};//TimeIntervalSetting
	
	
	public static void IfTimeRange () {
		
		System.out.println("\n============> IF TIME RANGE BEGINS <================\n");
		
		//Search for the Start Date field
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("start_date_input", 120);
		WaitAndNotify.StartWait("If Time Range > Search for the \"start_date_input\" field");
		
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.sendKeys(TestDataStorage.START_DATE_);
		
		//Search for the End Date field
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("end_date_input", 120);
		WaitAndNotify.StartWait("If Time Range > Search for the \"end_date_input\" field");
		
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.sendKeys(TestDataStorage.END_DATE_);
		
		//Click View Report button
		ProgramFiles.GloballyUsedClasses.ViewReportButton.ClickViewReportButton();
				
		//Releases a wait in Dashboard test
		WaitAndNotify.Notify("If Time Range");
		
	};//IfTimeRange
		
}
