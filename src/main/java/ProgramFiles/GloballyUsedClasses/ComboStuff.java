package ProgramFiles.GloballyUsedClasses;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ComboStuff {
	
	private static String comboTitle;
	private static String comboTarget;

	public static void comboBox(ArrayList<String> browsersList_) throws InterruptedException {
		
		System.out.println("\n============> COMBO BOX <================\n");

		comboTarget = "browser you want to run a test on";
		
		comboTitle = "<html><body>"
				+ "<font size='15px' family='Helvetica, sans-serif'>"
				+ "<i>Please select the " + comboTarget + ".</i>"
				+ "</font></body></html>";
				
		ComboBoxBuilder.main(comboTitle, browsersList_);
		
		while (ComboBoxBuilder.timeFlag != true) {
			
			for (int i = 0; i < 1000; i++) {
				
				TimeUnit.SECONDS.sleep(1);
				
				i++;
				if (ComboBoxBuilder.timeFlag == true) {
					
					WaitAndNotify.Notify("Combo box");
					
					break;
					
				}
				else if (i >= 1000) {
					
					System.exit(-1);
					System.out.println("FAILED!!! Time is out.");
					
				}// if
			}// for
		}// while
	}// comboBox
}//class
