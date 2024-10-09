package ProgramFiles.GloballyUsedClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;;

public class MoveElementIntoViewport {
	
	public static WebDriver webDriver = WebDriverConfiguration.webDriver;

	public static void MoveIntoViewport(WebElement webElement_) throws InterruptedException {
		
		TimeUnit.SECONDS.sleep((int) Math.round(ProgramFiles.GloballyUsedClasses.NetTrafficControl.rateToInterval * 0.1 ));
		
		System.out.println(" =========== MOVE INTO VIEW PORT ========== ");
				
		((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView(false)", webElement_);
		
		WaitAndNotify.Notify("MoveElementIntoViewport");
		
	}//MoveIntoViewport
	
}
