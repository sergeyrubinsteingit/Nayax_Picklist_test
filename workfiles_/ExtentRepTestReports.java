package ProgramFiles;

import java.io.IOException;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentRepTestReports
	extends org.testng.TestListenerAdapter implements org.testng.IInvokedMethodListener
{	
	static ExtentTest extentTest;
	static ExtentTest extentTestNode;
//	static ITestResult ITestResult;
	public static WebDriver webDriver = LoginIntoDcs.webDriver;
	
	public static ITestResult iTestResult;

	//Sets a set status: passed / failed
	@Test (alwaysRun = true)
	public static void passedORfailed (boolean testFlag, String screenName, String...strings ) throws IOException {
		
		System.out.println(" =========== EXTENT REPORT: TEST ========== ");
				
		try {
			extentTest = ExtentRepHTMLsetting.extentReports
				.createTest("Test of the screen " + screenName)
				.createNode("The tab " + strings);
			
			System.out.println(" =========== testFlag :::: " + testFlag + " :::::::");
			
			if (testFlag == false) {
				Assert.assertFalse(testFlag, "<<<<<<<< ASSERT FALSE >>>>>>>>");
				extentTest.log(Status.FAIL, "<<<<<<<< TEST FAILED >>>>>>>>");
			} else {
				Assert.assertTrue(testFlag,  "<<<<<<<< ASSERT TRUE >>>>>>>>");
				extentTest.log(Status.PASS, "<<<<<<<< TEST PASSED >>>>>>>>");
			};//if
		} catch (Exception e) {
			System.out.println("\n =========== EXTENT REPORT: THE FLOW'S FAILED ========== \n");
			e.printStackTrace();
		}
		
//		representationSettings (iTestResult);
		System.out.println(" =========== EXTENT REPORT: END OF THE TEST ========== ");
		
	}; //passedORfailed
	
	// Defines representation settings
//	@AfterTest (alwaysRun = true)
	@AfterMethod
	public static void representationSettings (ITestResult iTestResult) throws IOException {
		
		System.out.println(" **=========== EXTENT REPORT: AFTER METHOD ==========** ");
		
		try {
			if (iTestResult.getStatus() == ITestResult.FAILURE) {
				System.out.println(" **=========== AFTER METHOD : FAILURE ==========** ");
				extentTest.log(Status.FAIL, MarkupHelper.createLabel("::::: " + iTestResult.getName() + " failed. :::::", ExtentColor.RED));
				System.out.println("<<<<<<<< ITEST RESULT : FAILURE  >>>>>>>>");
			}
			else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
				System.out.println(" **=========== AFTER METHOD : SUCCESS ==========** ");
				extentTest.log(Status.PASS, MarkupHelper.createLabel("::::: " + iTestResult.getName() + " succeeded. :::::", ExtentColor.GREEN));
				System.out.println("<<<<<<<< ITEST RESULT : SUCCESS  >>>>>>>>");
			}
			else {
				System.out.println(" **=========== AFTER METHOD : SKIP ==========** ");
				extentTest.log(Status.SKIP, MarkupHelper.createLabel("::::: " + iTestResult.getName() + " was skipped. :::::", ExtentColor.AMBER));
				System.out.println("<<<<<<<< ITEST RESULT : SKIP  >>>>>>>>");
			}//if
		} catch (Throwable Throwable) {
			System.out.println(" **=========== AFTER METHOD : ERROR ==========** ");
			extentTest.log(Status.ERROR, Throwable.getMessage());
		}//try
		
	}; //representationSettings
	
	//Closes HTML Extent Report
	@AfterClass
	static void tearDown () {
//        extent.endTest(logger);

		ExtentRepHTMLsetting.extentReports.flush();
	};//tearDown

}
