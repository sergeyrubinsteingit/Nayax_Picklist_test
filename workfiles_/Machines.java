package testCases;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import ProgramFiles.MenuItemsList;
import ProgramFiles.OpenSelectedScreen;
import ProgramFiles.LoginIntoDcs;
import ProgramFiles.ScreenShotsMaker;
import ProgramFiles.WaitAndNotify;
import ProgramFiles.AppendScreenshotsToHTML;
import ProgramFiles.ExtentRepTestReports;
 

public class Machines {
	
	static WebDriver webDriver = LoginIntoDcs.webDriver;
	static Actions actions_ = new Actions(webDriver);
	static boolean awaitTestFinish = OpenSelectedScreen.awaitTestFinish = true;
	static Object objectForSynch = OpenSelectedScreen.objectForSynch = new Object();
	static WebElement stockTab = null;
	static WebElement conf_ok_btn = null;
	static WebElement fullEmptyEntry = null;
	static JavascriptExecutor javascriptExecutor = (JavascriptExecutor)webDriver;
	public static boolean statusFlag;
	
	@Test
	public static void TestSets(String className) {
		System.out.println(" =========== TEST SETS for MachinesTestCase BEGAN ========== ");
		try {
			
			List<WebElement> k_link = webDriver.findElements(By.className("k-link"));
			int startIndex = 0;
			// Removes element with no text
			RemoveNoTextElements (k_link);
			//Waiting for a function RemoveNoTextElements to end
			WaitAndNotify.StartWait();
			for (int ii = 0; ii < k_link.size(); ii++) {
				WebElement k_link_text = k_link.get(ii);

				if (k_link_text.getText().contains("Dashboard")) {
					// Index of the first tab in Machine's options
					startIndex = k_link.indexOf(k_link_text );
				} //if
	
			} //for
	
			System.out.println(" startIndex :::>> " + startIndex);
					
			//Click Products
			for ( int i = startIndex; i < k_link.size(); i++ ) {
				System.out.println(" k_link.get(i) :::%%%%> " + k_link.get(i).getText());			
				if (k_link.get(i).getText().contains("Products Map")) {
					actions_.moveToElement(k_link.get(i)).click().perform();
				} //if
			};//for
					
			//Checking Stock tab presence
			stockTab = (WebElement) new FluentWait(webDriver)
		        .withTimeout(30,TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(Exception.class)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Stock']")));
			// Move to the Stock tab
			actions_.moveToElement(stockTab).build().perform();
			
				TimeUnit.MILLISECONDS.sleep(1);
			
			//Drop menu entries
			List<WebElement> menu_labels = webDriver.findElements(By.className("k-item")
					.className("k-link")
					.tagName("label"));
			
			TimeUnit.MILLISECONDS.sleep(15);
			
			// Removes element with no text
			RemoveNoTextElements (menu_labels);
//			//Waiting for a function RemoveNoTextElements to end
			WaitAndNotify.StartWait();
			
			// Move to the Stock tab
			actions_.moveToElement(stockTab).build().perform();
		
			
			for (WebElement lbl : menu_labels) {
				System.out.println(" menu_labels :::>>> " + lbl.getText());
			};//for	
			
			String expectedCellColor = "";
			String actualCellColor = "";
			String redGreenClassNames [] = {"on_hand_container red_bg","on_hand_container green_bg","on_hand_container red_bg"};			


			try {;
				for (int i = 0; i < 2; i++) {
					//Checking for the visibility of the Red div = Empty Machine status
					fullEmptyEntry = (WebElement) new FluentWait(webDriver)
				        .withTimeout(3,TimeUnit.SECONDS)
				        .pollingEvery(1, TimeUnit.SECONDS)
				        .ignoring(Exception.class)
						.until(ExpectedConditions.visibilityOfElementLocated(By
						.cssSelector("div[class=' "+ redGreenClassNames[0] +"']")));  // That mean the status = "empty"
					
					TimeUnit.MILLISECONDS.sleep(50);
					List <WebElement> greenFullDivs = LoginIntoDcs.webDriver.findElements(By.cssSelector("div.on_hand_container.green_bg"));
					List <WebElement> redEmptyDivs = LoginIntoDcs.webDriver.findElements(By.cssSelector("div.on_hand_container.red_bg"));
					System.out.println(" \n<<<-####-  redEmptyDivs.size() =  "+redEmptyDivs.size()+"  -####->>>\n ");
					System.out.println(" \n<<<-####-  greenFullDivs.size() =  "+greenFullDivs.size()+"  -####->>>\n ");
					
					// In case the status = "empty"
					javascriptExecutor.executeScript("arguments[0].click();", fullEmptyEntry);
					TimeUnit.MILLISECONDS.sleep(25);
					
					ClickOKbutton ("After " + menu_labels.get(6).getText().toUpperCase() + ", var 1");// Confirms the choice by clicking OK button
					WaitAndNotify.StartWait();
					System.out.println(" <<<-####- Machines Test: ClickOKbutton  -####->>> ");
					
					//Screenshot
					ProgramFiles.ScreenShotsMaker.ScreenShots(menu_labels.get(6).getText().toUpperCase(), className);
					WaitAndNotify.StartWait();
					System.out.println(" <<<-####- Machines Test: ScreenShotsMaker.ScreenShots  -####->>> ");
					
					System.out.println(" <<<-&&& "+ menu_labels.get(6).getText().toUpperCase() +" had been clicked &&&->>> ");

				}//for
				WaitAndNotify.NotifyAll();
											
			} catch (Exception e) { // In case the status = "full"
				try {
					for (int i = 7; i >= 6; i--) {
						System.out.println(" <<<-####->>> "+ i +" <<-####->>> ");
						// Using [JavascriptExecutor] as no other way worked due to permanent overlay of some other element (gray screen) over the desired element.
						fullEmptyEntry = (WebElement) new FluentWait(webDriver)
					        .withTimeout(15,TimeUnit.SECONDS)
					        .pollingEvery(3, TimeUnit.SECONDS)
					        .ignoring(Exception.class)
							.until(ExpectedConditions.elementToBeClickable(By
							.xpath(" //*[text()='" + menu_labels.get(i).getText().toString() + "'] ")));
						
						javascriptExecutor.executeScript("arguments[0].click();", fullEmptyEntry);
						TimeUnit.MILLISECONDS.sleep(25);
						
						System.out.println(" <<<-#### "+ menu_labels.get(i).getText().toUpperCase() +" click was performed ####->>> ");
												
						// Confirms the choice by clicking OK button
						String currentLabel = menu_labels.get(i).getText().toUpperCase().toString();
						ClickOKbutton ("After " + currentLabel + ", var 2");						
						WaitAndNotify.StartWait();
						
						TimeUnit.SECONDS.sleep(4);//////////////////////////////////////////////////////////////////////////////////////////////////////////////////						
						//Draws border around the element
						if (i == 7) {
							List <WebElement> redEmptyDivs = LoginIntoDcs.webDriver.findElements(By.cssSelector("div.on_hand_container.red_bg"));
							System.out.println(" \n<<<-####-  redEmptyDivs.size() =  "+redEmptyDivs.size()+"  -####->>>\n ");
							TimeUnit.MILLISECONDS.sleep(25);
//							int x = 2;
							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//							String[] hexValue = color.replace(“rgba(“, “”).replace(“)”, “”).split(“,”);                           
//
//							hexValue[0] = hexValue[0].trim();
//
//							int hexValue1 = Integer.parseInt(hexValue[0]);                   
//
//							hexValue[1] = hexValue[1].trim();
//
//							int hexValue2 = Integer.parseInt(hexValue[1]);                   
//
//							hexValue[2] = hexValue[2].trim();
//
//							int hexValue3 = Integer.parseInt(hexValue[2]);                   
//
//							String actualColor = String.format(“#%02x%02x%02x”, hexValue1, hexValue2, hexValue3);
							
							////////////////////////////////////////////////////////////////////////////////////////////////
							for (WebElement redDiv : redEmptyDivs) {
								System.out.println(" \n<<<-&&&&&&&-  redDiv.getCssValue(\"background-color\") is  "+redDiv.getCssValue("background-color")+"  -&&&&&&&->>>\n ");
//								if (redDiv.getCssValue("visibility") == "visible" || redDiv.getCssValue("display") == "block") {
//								if (redDiv.getCssValue("background-color") == "rgba(216, 67, 67, 1)") {
								if (!redDiv.getCssValue("background-color").equals("#D84343")) {
//								if (Color.fromString(redDiv.getCssValue("background-color")).asHex()   == "#D84343") {
//									if (Color.fromString(redDiv.getCssValue("background-color")).asHex().equals("#D84343")) {
//								if (2*x==4) {
									((JavascriptExecutor)LoginIntoDcs.webDriver)
									.executeScript("arguments[0].style.border='2px solid #1d6e44'", redDiv);
									statusFlag = true;
								} else {
									((JavascriptExecutor)LoginIntoDcs.webDriver)
									.executeScript("arguments[0].style.border='2px solid #9c1a23'", redDiv);
									statusFlag = false;
								};//if
							};//for
						} else {
							List <WebElement> greenFullDivs = LoginIntoDcs.webDriver.findElements(By.cssSelector("div.on_hand_container.green_bg"));
							System.out.println(" \n<<<-####-  greenFullDivs.size() =  "+greenFullDivs.size()+"  -####->>>\n ");
							TimeUnit.MILLISECONDS.sleep(25);
							for (WebElement greenDiv : greenFullDivs) {
								System.out.println(" \n<<<-&&&&&&&-  greenDiv.getCssValue(\"background-color\") is  "+greenDiv.getCssValue("background-color")+"  -&&&&&&&->>>\n ");
								if (greenDiv.getCssValue("background-color") == "rgba(76, 207, 83, 1)") {
									((JavascriptExecutor)LoginIntoDcs.webDriver)
									.executeScript("arguments[0].style.border='3px solid #1d6e44'", greenDiv);
									statusFlag = true;
								} else {
									((JavascriptExecutor)LoginIntoDcs.webDriver)
									.executeScript("arguments[0].style.border='3px solid #9c1a23'", greenDiv);
									statusFlag = false;
								};//if
							};//for
						};//if
						
						TimeUnit.SECONDS.sleep(3);
						
						//Screenshot
						ProgramFiles.ScreenShotsMaker.ScreenShots(currentLabel, className);
						WaitAndNotify.StartWait();
						
						TimeUnit.SECONDS.sleep(2);
						
						//Fires Extent Report
						ProgramFiles.ExtentRepTestReports.passedORfailed(statusFlag, className, currentLabel);
						
						TimeUnit.SECONDS.sleep(1);

						//Probably hovers a mouse over Stock tab
						String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
								+"evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} "
								+"else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
						
						javascriptExecutor.executeScript(mouseOverScript, stockTab);
					
						System.out.println(" <<<-&&& Machine was filled up &&&->>> ");
					}; //for
					
					TimeUnit.SECONDS.sleep(3);
					
					//Appends to HTML report a div containing screenshots 
					AppendScreenshotsToHTML.AppendNewDiv(className);
					WaitAndNotify.StartWait();

				} catch (Exception e1) {
							System.out.println(" <<<-&&& Menu's flow failed. &&&->>> ");
					e1.printStackTrace();
					webDriver.quit();
					System.exit(-1);
				}
			} //try
		
			System.out.println(" =========== TEST SETS for MachinesTestCase ENDED ========== ");
			WaitAndNotify.NotifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//try
	}//TestSets
	
	static Object RemoveNoTextElements (List<WebElement> k_link) {
		// Removes element with no text
		for (int ii = 0; ii < k_link.size(); ii++) {
			WebElement k_link_text = k_link.get(ii);
			if (k_link_text.getText().length() == 0) {
				k_link.remove(k_link_text);
				ii--;
			}//if
		};//for
		System.out.println(" \n<<<-   RemoveNoTextElements: NotifyAll   ->>>\n ");
		WaitAndNotify.NotifyAll();
		return k_link;
	}//RemoveNoTextElements
	
	//Confirms the choice by clicking OK button
	static void ClickOKbutton (String infoString) throws InterruptedException {
		
		// Using [JavascriptExecutor] as no other way worked due to permanent overlay of some other element (gray screen) over the desired element.
		WebElement conf_ok_btn = (WebElement) new FluentWait(webDriver)
		        .withTimeout(15,TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(Exception.class)
				.until(ExpectedConditions.elementToBeClickable(By
				.className("k-widget")
				.className("k-window-content")
				.id("conf_ok_btn")));
		
		if (conf_ok_btn.isDisplayed()) {
			TimeUnit.MILLISECONDS.sleep(1);
			javascriptExecutor.executeScript("arguments[0].click();", conf_ok_btn);
			System.out.println(" <<<-@@@ Clicking OK button for @@@->>>\n " + infoString);
			WaitAndNotify.NotifyAll();
		} else {
			System.out.println(" No dialog window to interact with. ");
			actions_.moveToElement(stockTab).build().perform();
		} //if
		
		WaitAndNotify.NotifyAll();
			
	}//ClickOKbutton
	
}//////////////////////////////////////////////////***END***/////////////////////////////////////////////////////
