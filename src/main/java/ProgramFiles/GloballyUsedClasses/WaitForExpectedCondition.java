package ProgramFiles.GloballyUsedClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;


public class WaitForExpectedCondition {
	
	static WebDriver webDriver = WebDriverConfiguration.webDriver;
	public static WebElement ExpectedElement;
	public static List<WebElement>  ExpectedElements;
	
	
	public static WebElement PresenceOfElementLocatedByLinkText (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT LOCATED BY LINK TEXT BEGINS >>>>>>>>>>>");
				
		try {
			
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			        .withTimeout(timeOut_,TimeUnit.SECONDS)
			        .pollingEvery(5,TimeUnit.SECONDS)
			        .ignoring(Exception.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText(element_)));
			
		} catch (Exception e) {
			
			System.err.println("Failed to find a present element located by link text");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT LOCATED BY LINK TEXT");
		
		return ExpectedElement;
		
	};//presenceOfElementLocatedByLinkText
		
	
	public static WebElement PresenceOfElementLocatedByPartialLinkText (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT LOCATED BY LINK TEXT BEGINS >>>>>>>>>>>");
				
		try {
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			        .withTimeout(timeOut_,TimeUnit.SECONDS)
			        .pollingEvery(5,TimeUnit.SECONDS)
			        .ignoring(Exception.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(element_)));
		} catch (Exception e) {

			System.err.println("Failed to find a present element located by partial link text");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT LOCATED BY LINK TEXT");
		
		return ExpectedElement;
		
	};//PresenceOfElementLocatedByPartialLinkText
	
	
	public static WebElement VisibilityOfElementLocatedByClassName (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT LOCATED BY CLASS NAME BEGINS >>>>>>>>>>>");
				
		try {
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			        .withTimeout(timeOut_,TimeUnit.SECONDS)
			        .pollingEvery(5,TimeUnit.SECONDS)
			        .ignoring(Exception.class)
			        .until(ExpectedConditions.visibilityOfElementLocated(By.className(element_)));
		} catch (Exception e) {

			System.err.println("Failed to find a visible element located by class name");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT LOCATED BY CLASS NAME");
		
		return ExpectedElement;
		
	};//visibilityOfElementLocatedByClassName
	
			
	public static WebElement PresenceOfElementLocatedByXpath (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT LOCATED BY XPATH BEGINS >>>>>>>>>>>");
				
		try {
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			        .withTimeout(timeOut_,TimeUnit.SECONDS)
			        .pollingEvery(5,TimeUnit.SECONDS)
			        .ignoring(Exception.class)
			        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(element_)));
		} catch (Exception e) {

			System.err.println("Failed to find a present element located by xpath");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT LOCATED BY XPATH");
		
		return ExpectedElement;
		
	};//PresenceOfElementLocatedByXpath
			
		
	public static WebElement ElementToBeClickableByXpath (String element_, int timeOut_) {
	
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT TO BE CLICKABLE LOCATED BY XPATH BEGINS >>>>>>>>>>>");
				
		try {
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			        .withTimeout(timeOut_,TimeUnit.SECONDS)
			        .pollingEvery(5,TimeUnit.SECONDS)
			        .ignoring(Exception.class)
			        .until(ExpectedConditions.elementToBeClickable(By.xpath(element_)));
		} catch (Exception e) {

			System.err.println("Failed to find a clickable element located by xpath");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT TO BE CLICKABLE LOCATED BY XPATH");
		
		return ExpectedElement;
	
	};//ElementToBeClickableByXpath
	
	
	public static WebElement ElementToBeClickableByClassNameId (String element_1, String element_2, String element_3, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT TO BE CLICKABLE LOCATED BY CLASS NAME & ID BEGINS >>>>>>>>>>>");
			
		try {
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			    .withTimeout(timeOut_,TimeUnit.SECONDS)
			    .pollingEvery(5,TimeUnit.SECONDS)
			    .ignoring(Exception.class)
			    .until(ExpectedConditions.elementToBeClickable(By.className(element_1).className(element_2).id(element_3)));
		} catch (Exception e) {

			System.err.println("Failed to find a present element located by class name");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT TO BE CLICKABLE LOCATED BY CLASS NAME & ID");
		
		return ExpectedElement;
	
	};//ElementToBeClickableByClassNameId
	
	
	public static WebElement ElementToBeClickableById (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT TO BE CLICKABLE LOCATED BY CLASS NAME & ID BEGINS >>>>>>>>>>>");
			
//		try {
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			    .withTimeout(timeOut_,TimeUnit.SECONDS)
			    .pollingEvery(5,TimeUnit.SECONDS)
			    .ignoring(Exception.class)
			    .until(ExpectedConditions.elementToBeClickable(By.id(element_)));
//		} catch (Exception e) {
//
//			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Failed to find a clickable element located by class name and id.");
//			
//		}
		
		WaitAndNotify.Notify("ELEMENT TO BE CLICKABLE BY ID");
		
		return ExpectedElement;
	
	};//ElementToBeClickableByClassNameId
	
	
	
	public static WebElement PresenceOfElementLocatedById (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT TO BE CLICKABLE LOCATED BY CLASS NAME & ID BEGINS >>>>>>>>>>>");
			
		try {
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			    .withTimeout(timeOut_,TimeUnit.SECONDS)
			    .pollingEvery(5,TimeUnit.SECONDS)
			    .ignoring(Exception.class)
			    .until(ExpectedConditions.presenceOfElementLocated(By.id(element_)));
		} catch (Exception e) {


			System.err.println("Failed to find a present element located by id");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT TO BE CLICKABLE LOCATED BY ID");
		
		return ExpectedElement;
	
	};//PresenceOfElementLocatedById
	
	
	
	public static WebElement PresenceOfElementLocatedByCssSelector (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT  LOCATED BY CSS SELECTOR BEGINS >>>>>>>>>>>");
			
		try {
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			    .withTimeout(timeOut_,TimeUnit.SECONDS)
			    .pollingEvery(5,TimeUnit.SECONDS)
			    .ignoring(Exception.class)
			    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(element_)));
		} catch (Exception e) {

			System.err.println("Failed to find a present element located by css selector");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT LOCATED BY CSS SELECTOR");
		
		return ExpectedElement;
	
	};//PresenceOfElementLocatedByCssSelector
	
		
	
	public static WebElement PresenceOfElementLocatedByClassName (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENT  LOCATED BY CLASS NAME BEGINS >>>>>>>>>>>");
			
		try {
			
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			    .withTimeout(timeOut_,TimeUnit.SECONDS)
			    .pollingEvery(5,TimeUnit.SECONDS)
			    .ignoring(Exception.class)
			    .until(ExpectedConditions.presenceOfElementLocated(By.className(element_)));
			
		} catch (Exception e) {

			System.err.println("Failed to find a present element located by class name");
			e.printStackTrace();
			throw e;

		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENT LOCATED BY CLASS NAME");
		
		return ExpectedElement;
	
	};//PresenceOfElementLocatedByCssSelector
	
		
	
	public static List<WebElement> PresenceOfAllElementsLocatedByXpath (String element_, int timeOut_) {
		
		System.out.println("<<<<<<<<<<< PRESENCE OF ELEMENTS  LOCATED BY XPATH BEGINS >>>>>>>>>>>");
			
		try {
			
			ExpectedElement = (WebElement) new FluentWait<WebDriver>(webDriver)
			    .withTimeout(timeOut_,TimeUnit.SECONDS)
			    .pollingEvery(5,TimeUnit.SECONDS)
			    .ignoring(Exception.class)
			    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(element_)));
			
		} catch (Exception e) {

			System.err.println("Failed to find all elements located by xpath");
			e.printStackTrace();
			throw e;
			
		}
		
		WaitAndNotify.Notify("PRESENCE OF ELEMENTS LOCATED BY XPATH");
		
		return ExpectedElements;
	
	};//PresenceOfElementLocatedByCssSelector
	

}//WaitForExpectedCondition
