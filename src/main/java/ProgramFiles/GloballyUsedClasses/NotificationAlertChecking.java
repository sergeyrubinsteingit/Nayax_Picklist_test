package ProgramFiles.GloballyUsedClasses;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import org.openqa.selenium.JavascriptExecutor;


public class NotificationAlertChecking {
	
	private static String [] notificationAlerts = {"div.notification.success", "div.notification.error", "div#success_notification"};	
	public static String notificationSelector = "notificationSelector"	;
	
	
	public static void NotificationAlertCheck() throws ParserConfigurationException, SAXException, IOException, TransformerException, InterruptedException {
		
		System.out.println("\n<<<<<<<<<<< NOTIFICATION ALERT CHECKING >>>>>>>>>>\n");
		
		System.out.println("\n<<<<<<<<<<<< notificationAlerts length is "+notificationAlerts.length+" >>>>>>>>>>>\n");

		for (int i = 0; i < notificationAlerts.length; i++) {
			
			try {
				// Waits for notification to appear
				ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedByCssSelector(notificationAlerts[i], 330);
				ProgramFiles.GloballyUsedClasses.WaitAndNotify.StartWait("Notification popup check");	
				
				((JavascriptExecutor)WebDriverConfiguration.webDriver).executeScript("arguments[0].scrollIntoView();", ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement);
				((JavascriptExecutor)WebDriverConfiguration.webDriver).executeScript("arguments[0].removeAttribute('style')", ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement);
				
				com.Start.TestProcedure.elementStateForTestReports = true;
				
				if (ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getSize() != null) {
					
					//Checks for error popup
					if (notificationAlerts[i].contains("error")) {
						
						com.Start.TestProcedure.elementStateForTestReports = false;
						
					};//if
					
					// draws a border around element
					ProgramFiles.GloballyUsedClasses.DrawBorderAroundElement.DrawBorder(ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement, com.Start.TestProcedure.elementStateForTestReports);
					ProgramFiles.GloballyUsedClasses.WaitAndNotify.StartWait("Notification Alerts");	
					
					// screen shots
					ProgramFiles.GloballyUsedClasses.ScreenShotTaker.ScreenShots("Notification popup", "Noti_", "notifications_", "/html/body", com.Start.TestProcedure.elementStateForTestReports);
					ProgramFiles.GloballyUsedClasses.WaitAndNotify.StartWait("notification Alerts: ScreenShotsMaker");	
					
					//Run TEST NG for the record in Extent Report
					RunTestNG.TestNgRun();
					WaitAndNotify.StartWait("Notifications: Run Test NG");
					
					WaitAndNotify.Notify("Notifications");
					
					break;
					
				} else {
					
					System.out.println("\n<<<<<<<<<<< No success notification. Test is shut down. >>>>>>>>>>>>\n");
					
					ProgramFiles.TestUsedClasses.InformationPopupPanel.InformationPopupPanel("No success notification. Test is shut down.", false);
					
					WebDriverConfiguration.webDriver.quit();
					
					System.exit(-1);
					
				};//if
				
			}catch (NoSuchElementException ne){
				
				System.out.println("\n<<<<<<<<<<< No element with id "+ notificationAlerts[i] +" >>>>>>>>>>>>\n");
				
				continue;
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}//try
			
		}//for
				
	}//NotificationAlertCheck

}