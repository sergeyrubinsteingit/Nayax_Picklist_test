package ProgramFiles.GloballyUsedClasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;


public class ZoomOutPage {
	
	public static void ZoomPage (int repetitions_, boolean inOrOut) {
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< ZOOM OUT PAGE >>>>>>>>>>>>>>>>>>>>>>>>");
								
		try {	
			
			Robot robot_ = new Robot();
			
			for (int i = 0; i < repetitions_; i++) {
				
			   robot_.keyPress(KeyEvent.VK_CONTROL);
			   
			   if (inOrOut) {
				   
				   robot_.keyPress(KeyEvent.VK_PLUS);
				   
			   } else {
				   
				   robot_.keyPress(KeyEvent.VK_MINUS);
				   
			   } // if
				   
			} // for
			
		} catch (NullPointerException | NoSuchElementException e) {
			
			e.printStackTrace();
			WebDriverConfiguration.webDriver.quit();
			System.exit(-1);
			
		} catch (AWTException e) {
			
			e.printStackTrace();
		}
		
	};//ZoomPage

}
