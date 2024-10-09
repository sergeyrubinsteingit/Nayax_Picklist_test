package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import ProgramFiles.AppendScreenshotsToHTML;
import ProgramFiles.ExtentRepTestReports;
import ProgramFiles.HtmlReportInNewTab;
import ProgramFiles.GloballyUsedClasses.*;
import ProgramFiles.LoginIntoDcs;
import ProgramFiles.RunTestNG;
import ProgramFiles.WaitAndNotify;


public class MachineStatus {
	
	public static List<WebElement> redCellList = new ArrayList<>();
	public static boolean statusFlag;
	private static WebDriver webDriver_ = LoginIntoDcs.webDriver;
	//Picks main div element
	private static WebElement uiMainTable = webDriver_.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[3]/table/tbody/tr/td[2]"));
	private static String msgTestResult_ = "";
	private static String msgBackgroundColor_ = "";
	private static String elementText = "";
	private static ExtentRepTestReports extentRepTestReports = new ExtentRepTestReports();

	
//	@Test
//	public static Object findTDcells(String menuItemsText, String classSimpleName) {
//		
//		System.out.println(" =========== MACHINE STATUS TEST ========== ");
//		
//		try {
////			
//			//Verifies presence of the machines data table
//			new FluentWait<WebDriver>(LoginIntoDcs.webDriver)
//	        .withTimeout(60,TimeUnit.SECONDS)
//	        .pollingEvery(1,TimeUnit.SECONDS)
//	        .ignoring(Exception.class)
//			.until(ExpectedConditions.presenceOfElementLocated(By.className("boundGrid")));
//			
//			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.25 ));
//						
//			//Collects table cells containing machine names
//			List<WebElement> machinesInColumn = webDriver_.findElements(By.cssSelector("td.val.read"));
//			
//			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.25 ));
//			
//			System.out.println(" <<<<<<<<<< machinesInColumn SIZE ::: "+  machinesInColumn.size() +" >>>>>>>>>> ");
//						
//			for (int i = 0; i< machinesInColumn.size(); i++) {
//				
//				System.out.println(" <<<<<<<<<< machinesInColumn ::: "+machinesInColumn.get(i).getText()+" >>>>>>>>>> ");
//				
//			};//for
//			
//			//Reads a number of machines
//			elementText = uiMainTable.getText();
//			
//			//Verifies presence of a machine name in table
//			String [] machineNamesToSearchFor = {"AddNewProduct","qwertyuiop0000"};
//			
//			for (int i = 0; i < machineNamesToSearchFor.length; i++ ) {
//				
//				for (int i1 = 0; i1< machinesInColumn.size(); i1++) {
//					
//					if (machinesInColumn.get(i1).getText().contains(machineNamesToSearchFor[i])) {
//						
//						System.out.println(" \n\n\n<<<<<<<<<< Machine names matches >>>>>>>>>>\n\n\n ");
//
//						msgTestResult_ = "<<<<<<<<<< Machine name  " + machineNamesToSearchFor[i] + "  matches the search >>>>>>>>>>";
//						msgBackgroundColor_ = "green";
//						
//						//Fires Extent Report
//						ProgramFiles.LoginIntoDcs.extentReportFlag = true;
//						LoginIntoDcs.classNameForReport = "<<<<<<<<<< Machine name  " + machineNamesToSearchFor[i] + "  matches the search >>>>>>>>>>";
//						extentRepTestReports.PassedOrFailedForExtentReport();
//						 
//						TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
//						
//						break;
//						
//					} else {
//						
//						if (i1 == machinesInColumn.size()-1) {
//							
////							ProgramFiles.InformationPopupPanel.InformationPopupPanel("Machine name  " + machineNamesToSearchFor[i] + "  doesn't match the search", false);
//							msgTestResult_ = "<<<<<<<<<< Machine name  " + machineNamesToSearchFor[i] + "  doesn't match the search >>>>>>>>>>";
//							msgBackgroundColor_ = "red";
//							
//							//Fires Extent Report
//							ProgramFiles.LoginIntoDcs.extentReportFlag = false;
//							LoginIntoDcs.classNameForReport = "<<<<<<<<<< Machine name  " + machineNamesToSearchFor[i] + "  doesn't match the search >>>>>>>>>>";
//							extentRepTestReports.PassedOrFailedForExtentReport();
//							 
//							TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
//							
//							break;
//							
//						} else {
//							
//							continue;
//							
//						}//if
//					
//					}//try 
//										
//				};//for
//				
//				//Organizes the resulting message
//				InnerHtmlSetting(msgTestResult_, msgBackgroundColor_);
//								
//				//Run test ng
//				RunTestNG.runTestNg();
//				WaitAndNotify.StartWait("MACHINE STATUS: RunTestNG");
//				 
//				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
//				
//				//Screenshot
//				ProgramFiles.ScreenShotsMaker.ScreenShots(menuItemsText, "TestClass_2", "//*[@id='uiMainTD']/div/div[4]/div/div[2]");
//				WaitAndNotify.StartWait("MACHINE STATUS: ScreenShotsMaker");
//								
//			};//for
//						
//			//Appends to HTML report a div containing screenshots 
//			AppendScreenshotsToHTML.AppendNewDiv(classSimpleName);
//			WaitAndNotify.StartWait("MACHINE STATUS: AppendScreenshotsToHTML");
//			
//			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.5 ));
//			
//			//At the end of whole tests
//			HTMLreportInNewTab.OpenHTMLreport();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//		
//	}//findTDcells
	
	
	private static void InnerHtmlSetting(String msgTestResult, String msgColor) {
		
		System.out.println(" =========== INNER HTML SETTING ========== ");
				
		((JavascriptExecutor)webDriver_).executeScript(
				
				"var htmlElements = arguments[0];"
				+ "htmlElements.innerHTML = \""+elementText+" <div style=\'position:absolute;top:40%;left:40%;"
//				+ "width:20%;height:auto;"
				+ "display:inline-block;z-index:10;padding: 5px 5px 5px 5px;"
				+ "color:#FFFFFF;text-shadow: 2px 2px 2px #000000;box-shadow: 2px 2px 2px #000000;"
				+ "font-weight:bold;background-color:"+msgColor+"\'>"+msgTestResult+"</div>\";"				
				, uiMainTable);//JavascriptExecutor
		
	};//InnerHtmlSetting
	
}//MachineStatus


//try {
//
//Assert.assertTrue(ProgramFiles.TestClasses.TestClass_1.selectedMachineName.contains(machinesInColumn.get(i).getText()));
//System.out.println(" \n\n\n<<<<<<<<<< Machine names matches >>>>>>>>>>\n\n\n ");
//ProgramFiles.InformationPopupPanel.InformationPopupPanel("Machine name  " + machinesInColumn.get(i).getText() + "  matches the search", true);
//break;
//
//} catch (Exception e) {
//
//continue;
//
//}//try 
