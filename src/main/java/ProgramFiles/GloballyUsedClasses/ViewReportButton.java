package ProgramFiles.GloballyUsedClasses;

import ProgramFiles.GloballyUsedClasses.WaitAndNotify;

public class ViewReportButton {
	
	public static void ClickViewReportButton() {
		
		//Search for the Click View Report Button
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("showReport", 120);
		WaitAndNotify.StartWait("Click View Report Button > Search for the \"View Report\" BUTTON");
		
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
		
	}

}
