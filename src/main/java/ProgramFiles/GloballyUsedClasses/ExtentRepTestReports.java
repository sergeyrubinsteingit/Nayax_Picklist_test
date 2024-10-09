package ProgramFiles.GloballyUsedClasses;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

//import com.aventstack.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentReports;

import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class ExtentRepTestReports extends org.testng.TestListenerAdapter implements org.testng.IInvokedMethodListener
{	
	static String pathToHTML = System.getProperty("user.dir") + "\\htmlReportsDir\\Extentreport.html";

	public static ExtentTest extentTest;
	public static ExtentTest extentTestNode;
	public static ExtentReports extentReport = new ExtentReports(pathToHTML, false);
	public static WebDriver webDriver = WebDriverConfiguration.webDriver;
	public static ITestResult iTestResult;
	public static String screen_Name;	
	//Updates its value after every test
	public static boolean extentReportFlag;
	
	
	@BeforeTest (alwaysRun = true)
	public  void StartTesting () {
		
		//Updates its value after every test
		extentReportFlag = com.Start.TestProcedure.elementStateForTestReports;
				
	};//StartTesting

	
	//Sets a set status: passed / failed
	@Test (alwaysRun = true)
	public  void PassedOrFailedForExtentReport () throws IOException {
		
		System.out.println("<<<<<<<<<<<<< EXTENT REPORT: Test from " + com.Start.TestProcedure.screenName + ", test flag is: " + extentReportFlag + " >>>>>>>>>>>>>");
				
		try {
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.1 ));
			
			if (extentReportFlag == false) {
				
				extentTest = ExtentRepHTMLsetting.extentReports.createTest("FAILED in test: " + com.Start.TestProcedure.screenName)
							.createNode(com.Start.TestProcedure.screenName);

				Assert.assertFalse(extentReportFlag);
				
				extentTest.log(Status.FAIL, "<<<<<<<< TEST FAILED >>>>>>>>");
				
			} else {
				
				extentTest = ExtentRepHTMLsetting.extentReports.createTest("PASSED in test: " + com.Start.TestProcedure.screenName)
							.createNode(com.Start.TestProcedure.screenName);
				
				Assert.assertTrue(extentReportFlag);

				extentTest.log(Status.PASS, "<<<<<<<< TEST PASSED >>>>>>>>");
				
			};//if
			
		} catch (Exception e) {
			
			System.out.println("<<<<<<<<<<<<< EXTENT REPORT: THE FLOW'S FAILED >>>>>>>>>>>>>");
			
			e.printStackTrace();
			
		}//try

		WaitAndNotify.NotifyAll("EXTENT REPORT: TEST");
		
		System.out.println("<<<<<<<<<<<<< EXTENT REPORT: END OF THE TEST >>>>>>>>>>>>>");
		
		//Reading html contents
		HtmlContentReader.HtmlReader(com.Start.TestProcedure.screenName);
		
	}; //passedORfailed
	
	
	// Defines representation settings
//	@AfterTest (alwaysRun = true)
	@AfterMethod (alwaysRun = true)
	public  void representationSettings (ITestResult iTestResult) throws IOException, InterruptedException {
		
		System.out.println("<<<<<<<<<<<<< EXTENT REPORT: Representation settings. The [ iTestResult ] is " + iTestResult.toString() + " >>>>>>>>>>>>>");
		
		try {
			
			if (iTestResult.getStatus() == ITestResult.FAILURE) {
				
				System.out.println("<<<<<<<<<<<<< AFTER METHOD, itest result : FAILURE >>>>>>>>>>>>>");
				
				extentTest.log(Status.FAIL, MarkupHelper.createLabel("::::: " + iTestResult.getName() + " failed. :::::", ExtentColor.RED));

			} else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
				
				System.out.println("<<<<<<<<<<<<< AFTER METHOD, itest result : SUCCESS >>>>>>>>>>>>>");

				extentTest.log(Status.PASS, MarkupHelper.createLabel("::::: " + iTestResult.getName() + " succeeded. :::::", ExtentColor.GREEN));

			} else {

				System.out.println("<<<<<<<<<<<<< AFTER METHOD, itest result : SKIP >>>>>>>>>>>>>");

				extentTest.log(Status.SKIP, MarkupHelper.createLabel("::::: " + iTestResult.getName() + " was skipped. :::::", ExtentColor.AMBER));

			}//if
			
		} catch (Throwable Throwable) {
			
			System.out.println("<<<<<<<<<<<<< AFTER METHOD, itest result : ERROR >>>>>>>>>>>>>");
			
			extentTest.log(Status.ERROR, Throwable.getMessage());
			
		}//try
		
	}; //representationSettings
	
	
	//Closes HTML Extent Report
//	@AfterClass (alwaysRun = true) @AfterMethod (alwaysRun = true)
	@AfterTest (alwaysRun = true)
	public  void tearDown () {

		System.out.println("<<<<<<<<<<<<< EXTENT REPORT: TEAR DOWN >>>>>>>>>>>>>");

		ExtentRepHTMLsetting.extentReports.flush();

	};//tearDown

}
