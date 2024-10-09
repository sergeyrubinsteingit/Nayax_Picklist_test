package ProgramFiles.GloballyUsedClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;

public class StaleElementIssue {
	
	static Actions actions_ = new Actions(WebDriverConfiguration.webDriver);

	public static void WaitForStaleElement(int case_, WebElement webel1, WebElement webel2, String str1) throws InterruptedException {

		System.out.println(" <<<<<<<<< WAIT FOR STALE ELEMENT BEGINS, CASE " + case_ + " >>>>>>>>>> ");
		
		//Repeat attempts if an element is stale
		boolean staleElementState = true;
		
		int counter1 = 0;
		
		  try{
			  
			while(staleElementState){	
				
				switch (case_) {
				
					case 1:
						
						//Draws border around an object
						if (webel1.getAttribute("class").toString().equals(str1)) {
							
							((JavascriptExecutor)WebDriverConfiguration.webDriver).executeScript("arguments[0].style.border='3px solid #1d6e44'", webel1);
							com.Start.TestProcedure.elementStateForTestReports = true;
							
						} else {
							
							((JavascriptExecutor)WebDriverConfiguration.webDriver).executeScript("arguments[0].style.border='3px solid #ff3300'", webel1);
							com.Start.TestProcedure.elementStateForTestReports = false;
							
						};//if
						
						break;
						
						
					case 2:
						
						//Probably hovers a mouse over Stock tab
						String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
								+"evObj.initMouseEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} "
								+"else if(document.createEventObject) { arguments[0].fireEvent('onmouseout');}";
						
						((JavascriptExecutor)WebDriverConfiguration.webDriver).executeScript(mouseOverScript, webel1);
						
						break;
						
						
					case 3:
						
						//To move a mouse out of Stock tab
						String mouseOut_ = "var createMouseEvents = document.createEvent('MouseEvents');"
								+ "createMouseEvents.initMouseEvent('mouseout', true, false); "
								+ "arguments[0].dispatchEvent(createMouseEvents);";
						
						((JavascriptExecutor)WebDriverConfiguration.webDriver).executeScript(mouseOut_, webel1);
						
						break;
	
						
						
					case 4:
						
						// Move to the Stock tab
						actions_.moveToElement(webel1).build().perform();
						
						break;
	
						
					default:
						
							System.out.println(" <<<<<<<<< Failed to find a tested element. Test is shut down. >>>>>>>>>> ");
							WebDriverConfiguration.webDriver.quit();
					    	System.exit(-1);
						
						break;
						
				}// switch

				//Breaks the While cycle	     
				staleElementState = false;
				
				WaitAndNotify.Notify("From Stale Element Issue");
				
				System.out.println(" <<<<<<<<< WAIT FOR STALE ELEMENT ENDS >>>>>>>>>> ");
			
			}//while

		  } catch(StaleElementReferenceException e){
			  
			//Continues the While cycle
		  	staleElementState = true;
		  	
		  	TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
		  	
		    counter1++;
		    
			    if (counter1 > 5) {
			    	
					System.out.println(" <<<<<<<<< Failed to find a tested element. Test is shut down. >>>>>>>>>> ");
					WebDriverConfiguration.webDriver.quit();
			    	System.exit(-1);
			    	
			    };//if
		  }//try		
	}	
}
