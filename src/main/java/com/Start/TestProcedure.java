//STARTS THE PROGRAM
package com.Start;

import java.awt.AWTException;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ProgramFiles.GloballyUsedClasses.CheckHTTPconnection;
import ProgramFiles.GloballyUsedClasses.CreateHtmlReportFiles;
import ProgramFiles.TestUsedClasses.CookieBannerClose;
import ProgramFiles.GloballyUsedClasses.ListBrowsers_;
import ProgramFiles.GloballyUsedClasses.MakeBrowsersList;
import ProgramFiles.GloballyUsedClasses.UpdateTBL_USERS;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;
import ProgramFiles.GloballyUsedClasses.TestDataStorage;
import ProgramFiles.GloballyUsedClasses.HtmlReportInNewTab;
import ProgramFiles.GloballyUsedClasses.WebDriverConfiguration;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import ProgramFiles.TestUsedClasses.AgreementFormTest;
import ProgramFiles.TestUsedClasses.MachinesProductMap;
import ProgramFiles.TestUsedClasses.MachinesSearchForTestMachine;


public abstract class TestProcedure implements KeyListener {
	
	//To be used later on in Extent Report for a flag passed / failed
	public static boolean elementStateForTestReports;
	
	//The name of screen for Extent Report
	public static String screenName;

	
	public static void main(String [] args) throws InterruptedException, IOException, AWTException, ParserConfigurationException, SAXException, TransformerException {
		
		System.out.println("<<<<<<<<<<<<<<  BEGINNING OF THE TEST >>>>>>>>>>>>>>");
		
		System.out.println("<<<<<<<<<<<<<<  TestDataStorage.LOGIN_URL : "+TestDataStorage.LOGIN_URL_+" >>>>>>>>>>>>>>");
						
		// to enable variables in enumerator
		TestDataStorage.TestKeywords();
		
		// SQL table, user state update
		UpdateTBL_USERS.Update_TBL_USERS();
		WaitAndNotify.StartWait("Table Users update");

		// listing browsers on the machine
		ListBrowsers_.RedefineBrowsersList();
		WaitAndNotify.StartWait("Renew the browsers list");
		
		//	arranges a LIST OF BROWSERS and then opens COMBO BOX
		MakeBrowsersList.ListSourceReader();
		WaitAndNotify.StartWait("Arranges the browsers list");
		
        // Checks HTTP connection
        CheckHTTPconnection.ConnectionStatus(TestDataStorage.LOGIN_URL_);
        WaitAndNotify.StartWait("Connection Status check");       
		
		//Creates 2 HTML files, one for screen shots presentation, the other for the Extent Report
		CreateHtmlReportFiles.createReportFiles();
		WaitAndNotify.StartWait("Test Cases Collection > Creating 2 HTML report files");
		
		// writes opening tags in screenshots report HTML file
		CreateHtmlReportFiles.createHtmlOpeningTags();
		WaitAndNotify.StartWait("Test Cases Collection > Opening HTML tags");
		
		//Starts settings for Extent Report
		ProgramFiles.GloballyUsedClasses.ExtentRepHTMLsetting.ExtentRepHtml();
        
		// configures a web driver
		WebDriverConfiguration.webDriverSettings();
		WaitAndNotify.StartWait("configure web driver");  
		
		// opens browser
		LoginIntoDcs.openBrowser();
		WaitAndNotify.StartWait("Open browser");
		
		// Cookies banner test
		CookieBannerClose.CloseCookieBanner();
		WaitAndNotify.StartWait("Test Cases Collection > Close Cookies banner");

		// New user agreement test:
		AgreementFormTest.waitForAgreementFormPresence();
		WaitAndNotify.StartWait("Test Cases Collection > AgreementFormTest");		
		
		// MachinesSearchForTestMachine
		MachinesSearchForTestMachine.SelectTestMachine();
		WaitAndNotify.StartWait("Machines Search For Test Machine");
		
		// Machines Product Map tests
		MachinesProductMap.productMapTests();
		WaitAndNotify.StartWait("Test Cases Collection > Machines. Test Sets");			
		
		// writes closing tags in screenshots report HTML file
		CreateHtmlReportFiles.CreateHtmlClosingTags();
		WaitAndNotify.StartWait("Test Cases Collection > Closing HTML tags");
		
		// Shows test results in new tabs:
		HtmlReportInNewTab.OpenHtmlReport();
		WaitAndNotify.StartWait("Test procedure > Open test results in the new tabs");
		
		// Exits the test
		System.exit(0);
		
	} //main

}// class