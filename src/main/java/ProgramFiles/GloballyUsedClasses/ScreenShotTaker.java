package ProgramFiles.GloballyUsedClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import org.openqa.selenium.JavascriptExecutor;

public class ScreenShotTaker {

	static String screenShotsPath = System.getProperty("user.dir") + "\\screenShots\\";
	static Boolean isElemDisplayed = WebDriverConfiguration.webDriver.findElement(By.className("k-input")).isDisplayed();
	static WebElement bodyElement = WebDriverConfiguration.webDriver.findElement(By.tagName("body"));
	private static int scrollTop = 0;
	public static String shotsForReport = "";
	
	
	// arranges a data for a screenshot
	public static void ScreenShots(String TestRecord, String fileName, String className, String cssSelector_, boolean elementStateForTestReports) {
		
		System.out.println("<<<<<<<<<<<<< SCREENSHOTS ARRANGER >>>>>>>>>>>>>");
		
		System.out.println("<<<< Screening for file " + screenShotsPath +"\\"+ className + "\\" + fileName.toString() + " >>>>");
		
		try {
			
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor)WebDriverConfiguration.webDriver;
			
			WebElement elementToScroll = null;
			
//			if(fileName != "Noti_") {
				
				elementToScroll = WebDriverConfiguration.webDriver.findElement(By.xpath(cssSelector_));
				
//			} else {
//				
//				elementToScroll = WebDriverConfiguration.webDriver.findElement(By.cssSelector(cssSelector_));
//				
//			};//if

			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));

			int scrollHeight = Integer.parseInt(elementToScroll.getAttribute("scrollHeight"));
			
			int clientHeight = Integer.parseInt(elementToScroll.getAttribute("clientHeight"));
			
			float modloScrollHeight = 0;
						
			if (clientHeight > 0)
			{
				
				modloScrollHeight = scrollHeight % clientHeight;
				
			} else {
				
				modloScrollHeight = scrollHeight;
				
			};//if
			
			float controlHeight = scrollHeight + modloScrollHeight ;
			
			System.out.println("<<< cssSelector_ = " + cssSelector_ + ".>>>");
			System.out.println("<<< scrollHeight = " + scrollHeight + ".  clientHeight = " + clientHeight + ".>>>");
			System.out.println("<<< modloScrollHeight = " + modloScrollHeight + ".>>>");			
			
//			if (scrollHeight == 0) {
//				
//				System.out.println("\n<<< scrollHeight = 0. System is shut down.>>> \n");
//				WebDriverConfiguration.webDriver.quit();
//				System.exit(-1);
//				
//			};//if

			//return to top
			javascriptExecutor.executeScript("arguments[0].scrollTo(0,0);", elementToScroll);
			
			// regulates a number of shots to take
			if (scrollHeight == clientHeight | cssSelector_ == "/html/body") {
				
				System.out.println("<<<<<<<<<<< Screenshot taker: Single shot scenario >>>>>>>>>>>>");
				
				// takes screen shot
				TakeShotAndSaveFile ( TestRecord, className, fileName, elementStateForTestReports);
				WaitAndNotify.StartWait("Take Shot And Save File");
				
				TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
				
			} else {
				
				System.out.println("<<<<<<<<<<< Screenshot taker: Multiple shots scenario >>>>>>>>>>>>");
				
				do {
					javascriptExecutor.executeScript("arguments[0].scrollTo(0," + scrollTop + ");", elementToScroll);
					
					// takes screen shot
					TakeShotAndSaveFile ( TestRecord, className, fileName, elementStateForTestReports);
					WaitAndNotify.StartWait("Take Shot And Save File");
					
					TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
									
					scrollTop = scrollTop + clientHeight;
					
					TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
									
					System.out.println("<<<<<<<<<<< scrollTop = "+scrollTop+" >>>>>>>>>>>>");
					
				} while (scrollTop <= scrollHeight);
				
			} // if

			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
			
			// adds a block to HTML report
			System.out.println("<<<<<<<<<<< AppendBlockToHtml from ScreenShotsTaker: Record is " + TestRecord + ", State is " + com.Start.TestProcedure.elementStateForTestReports + " >>>>>>>>>>>>");
			CreateHtmlReportFiles.AppendBlockToHtml(shotsForReport, TestRecord, elementStateForTestReports);
			WaitAndNotify.StartWait("Screen shotsr");
			
			// clears for further records
			shotsForReport = "";
			
			scrollTop = 0;
			
			WaitAndNotify.Notify("SCREENSHOTS");
			
		} catch (IOException e) {
			
			System.out.println("Failed to save file " + screenShotsPath + fileName.toString() + ".png");
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}//try 

	}//ScreenShotArranger
	
	
	// screenshot taker
	static File TakeShotAndSaveFile (String TestRecord, String className, String fileName, boolean elementStateForTestReports) throws IOException {
		
		System.out.println("<<<<<<<<<<<<< SCREENSHOTS TAKER >>>>>>>>>>>>>");
		
		//Makes screenshots
		File shotFile = ((TakesScreenshot)WebDriverConfiguration.webDriver).getScreenshotAs(OutputType.FILE);
		
		String timestamp_ = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		
		// img src model
		String imgSrc = screenShotsPath + "\\" + className + "\\" + fileName + "_" +timestamp_+ "_.png";
		
		FileUtils.copyFile(shotFile, new File(imgSrc));
		
		shotsForReport += "<div class=\"PictureHolder\" style='position:relative;top:0px;left:0px;width:15%;height:auto;'>"
				+ "<img src='" + imgSrc + "' class=\"TestScreenShots\" onclick=\"javascript:EnlargeImage(this.src);\" /></div>";		
		
		WaitAndNotify.Notify("Take Shot And Save File");
		
		return shotFile;
		
	} // TakeShotAndSaveFile
	
	
	//Delete dir if it exists
	static boolean DeleteDirIfExists (File ScreenshotsDir, String className) {
				
		if (ScreenshotsDir.isDirectory()) {
			
			String [] FilesInDirectory = ScreenshotsDir.list();
			
			for (int i = 0; i < FilesInDirectory.length; i++) {
				
				boolean IsSuccessfullyDeleted = DeleteDirIfExists (new File (ScreenshotsDir, FilesInDirectory[i]), className);
				
				if (!IsSuccessfullyDeleted) {
					return false;
				};//if
				
			};//for
			
		};//if
		
			WaitAndNotify.Notify("Delete Dir If Exists");
			
			return ScreenshotsDir.delete();
		
	};//DeleteDirIfExists
	
}// ScreenShotsMaker