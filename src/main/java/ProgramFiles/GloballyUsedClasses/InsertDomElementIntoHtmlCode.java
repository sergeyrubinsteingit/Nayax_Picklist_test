package ProgramFiles.GloballyUsedClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;

public class InsertDomElementIntoHtmlCode {
	
	public static Object InsertDivWithMessage (boolean isPassed_, String Message_, WebElement DomElementToAttachTo_) throws InterruptedException {
		
		System.out.println("<<<<<<< INSERT DIV WITH MESSAGE BEGINS >>>>>>>");
		
		System.out.println("<<<<<<< isPassed_ = " + isPassed_);
		
        String PopupColor = "";

        if (isPassed_ == true) {

            PopupColor = "#94f736"; // "green"; // 

        } 
        else 
        {

            PopupColor = "#f77036"; //"red"; //

        } //if
		
		Object DivWithMessage = ((JavascriptExecutor )WebDriverConfiguration.webDriver).executeScript(
				
					"var htmlElements = arguments[0];"
	                    + "htmlElements.innerHTML += \" <div class=\'TestResultDisplay\' style=\'position:absolute;top:50%;left:50%;"
	                    + "transform:translate(-50%,-50%); width:50%;height:auto;"
	                    + "display:inline-block;z-index:10;padding: 5px 5px 5px 5px;"
	                    + "box-shadow: 2px 2px 7px #999999;text-align: center;"
	                    + "font-weight:bold;background-color:" + PopupColor + "\'>" + Message_ + "</div>\";"
	                    , DomElementToAttachTo_
				);
		
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.5 ));	
		
		// takes a screen shot
		ProgramFiles.GloballyUsedClasses.ScreenShotTaker.ScreenShots("PickList: map bins vs pdf records", "PickListTest", "MachinesProductMap", "//div[@class='TestResultDisplay']", com.Start.TestProcedure.elementStateForTestReports);
		WaitAndNotify.StartWait("Insert DOM element: shot");
		
		//Run TEST NG for the record in Extent Report
		com.Start.TestProcedure.screenName = "Picklist: map bins vs pdf records";
		RunTestNG.TestNgRun();
		WaitAndNotify.StartWait("Insert DOM element: Run Test NG");
		
	    // delete an element if exists
        if (WebDriverConfiguration.webDriver.findElements(By.cssSelector("div.TestResultDisplay")).size() > 0) {
        	
        	
        	((JavascriptExecutor )WebDriverConfiguration.webDriver).executeScript(
        			
    			"document.getElementsByClassName('TestResultDisplay')[0].remove();", DomElementToAttachTo_
        			
        	);
        	
        }//if
				
		WaitAndNotify.Notify("From Insert Dom Element Into Html Code");
		
		return DivWithMessage;
		
	} // InsertDivWithMessage

} // InsertDomElementIntoHtmlCode
