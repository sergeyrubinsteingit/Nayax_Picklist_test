package ProgramFiles.GloballyUsedClasses;

public class OnTestFailure {
	
	public static void OnTestFailureSystemExit(Exception exception_, String theMessage) {
		
		System.err.println("\n<<<<<<<<<<< On Test Failure >>>>>>>>>>>>\n");
		
		System.out.println("\n<<<<<<<<<<< " + theMessage + " >>>>>>>>>>>>\n");
		exception_.printStackTrace();
		WebDriverConfiguration.webDriver.quit();
		System.exit(-1);
		
	}//OnTestFailure

}
