package ProgramFiles.GloballyUsedClasses;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;


public class HtmlReportInNewTab {
	
	public static void OpenHtmlReport() throws InterruptedException {
		
		System.out.println("<<<<<<<<<<<<<<<<< OPEN HTML REPORTS IN NEW TAB >>>>>>>>>>>>>>>>>");
		
		//Opens HTML report in a new tab
		String [] htmlReports = {"Screenshots.html", "Extentreport.html",};
		
		for (int i = 0; i < 2; i++) {
			
			String pathToHTML = System.getProperty("user.dir") + "\\htmlReportsDir\\" + htmlReports[i];
			
			// opens a new tab
			((JavascriptExecutor) WebDriverConfiguration.webDriver).executeScript("window.open()");
			
			// writes down this tab's index into list
			ArrayList<String> openTabs = new ArrayList<String>(WebDriverConfiguration.webDriver.getWindowHandles());
			
			// switches to the last tab of screenshots
			WebDriverConfiguration.webDriver.switchTo().window(openTabs.get(openTabs.size()-1));
			
			// opens an html page in this tab
			WebDriverConfiguration.webDriver.navigate().to(pathToHTML);

			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
			
			//Closes the alert on a new tab
//		    Alert alert = LoginIntoDcs.webDriver.switchTo().alert();
//		    alert.accept();
//		    
//			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
		    
		    // switches one tab back to Extent Report
			WebDriverConfiguration.webDriver.switchTo().window(openTabs.get(openTabs.size()-2));

		};//for
		
		WaitAndNotify.Notify("from Open HTML reports in new tab");

	}//OpenHTMreport
}
