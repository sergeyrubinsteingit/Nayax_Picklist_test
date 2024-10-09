package ProgramFiles.GloballyUsedClasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import org.openqa.selenium.JavascriptExecutor;		


public class ScreenShotsTaker {

	static WebDriver webDriver = LoginIntoDcs.webDriver;
	static String screenShotsPath = System.getProperty("user.dir") + "\\screenShots\\";
	static Boolean isElemDisplayed = webDriver.findElement(By.className("k-input")).isDisplayed();
	static WebElement bodyElement = webDriver.findElement(By.tagName("body"));
	
	
	public static void ScreenShots(String fileName) {
		
		System.out.println(" =========== SCREENSHOTS MAKER ========== ");
		
		System.out.println("<<<< Screening for file " + screenShotsPath + fileName.toString() + " >>>>");
		
		try {
			Robot robot = new Robot();
			// Zooming view in
				for (int i = 0; i<2; i++) {					
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_MINUS);
					TimeUnit.MILLISECONDS.sleep(5);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_MINUS);
					TimeUnit.MILLISECONDS.sleep(5);
				}//for
			
			String shortcutGoToFullScreen = Keys.chord(Keys.F12);
			webDriver.findElement(By.tagName("body")).sendKeys(shortcutGoToFullScreen);

			//Taking shots
			((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			File shotFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(shotFile, new File(screenShotsPath + fileName.toString() + ".png"));
			TimeUnit.MILLISECONDS.sleep(25);
			
			//Zoom out to 0
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_0);
			
		} catch (IOException e) {
			System.out.println("Failed to save file " + screenShotsPath + fileName.toString() + ".png");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//ScreenShots
	
}// ScreenShotsMaker