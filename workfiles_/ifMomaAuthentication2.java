package ProgramFiles;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import ProgramFiles.LoginIntoDcs;

public class ifMomaAuthentication {
	
	public static void IfMoma () {
		
		try {
			WebElement nayax_spinner = (WebElement) new FluentWait<WebDriver>(LoginIntoDcs.webDriver)
					.withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(Exception.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.className("nayax-spinner")));
			
			if(nayax_spinner != null) {
				
				WebElement setSmsForTotp = (WebElement) new FluentWait<WebDriver>(LoginIntoDcs.webDriver)
						.withTimeout(60, TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(Exception.class)
						.until(ExpectedConditions.presenceOfElementLocated(By.id("setSmsForTotp")));
				
				Actions actions_ = new Actions(LoginIntoDcs.webDriver);
				actions_.moveToElement(setSmsForTotp).click().perform();
				TimeUnit.MILLISECONDS.sleep(500);
				
			};//if
		} catch (Exception e) {
			System.out.println("\n<<<<<<<<<<< Failed to pass in Moma Authentication. The test is shut down >>>>>>>>>>>>\n");
			e.printStackTrace();
			LoginIntoDcs.webDriver.quit();
			System.exit(-1);
		}//try
		
		WaitAndNotify.Notify("Notify from if Moma Authentication");
		
		return;
		
	};//IfMoma

}//ifMomaAuthentication
