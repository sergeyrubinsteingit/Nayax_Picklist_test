package ProgramFiles.GloballyUsedClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class DrawBorderAroundElement {
	
	public static void DrawBorder(WebElement testedElement, boolean elementStateMarker) {
		
		System.out.println("\n<<<<<<<<  DRAW BORDER AROUND THE ELEMENT  >>>>>>>>\n");
		
		try {
			// For the border color code
			String borderColor;
			
			// Selects color 
			if (elementStateMarker == true) {
				
				borderColor = "#94f736";  // Green
				
			}
			else 
			{
				
				borderColor = "#f77036";  // Red
				
			};//if
			
			// Draws a border
			((JavascriptExecutor) WebDriverConfiguration.webDriver).executeScript("arguments[0].style.border='3px solid "+ borderColor +"'", testedElement);
			
			// ReleASES waiting
			WaitAndNotify.Notify("DRAW BORDER AROUND THE ELEMENT");
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}//DrawBorder

}// DrawBorderAroundElement
