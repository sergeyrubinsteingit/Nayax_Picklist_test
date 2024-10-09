package ProgramFiles.TestUsedClasses;

import ProgramFiles.GloballyUsedClasses.TestDataStorage;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;

public class SalesSummaryTotalTransactionAmount {
	
	public static void SearchForTestMachine() {
		
		//Search for the "Actor" field
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.PresenceOfElementLocatedById("actor_id_input", 120);
		WaitAndNotify.StartWait("Search For Test Machine > Search for the \"Actor\" field");
		
		String selectedActor = TestDataStorage.LOGIN_URL_.contains("qa2") || TestDataStorage.LOGIN_URL_.contains("qa1") ? TestDataStorage.ACTOR_QA_ : TestDataStorage.ACTOR_PROD_;
		
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.sendKeys(selectedActor);
		
		// Setting the time range
		try {
			
			ProgramFiles.GloballyUsedClasses.TimeRangeSetting.TimeIntervalDropdown();
			WaitAndNotify.StartWait("Search For Test Machine > Fires \"TimeIntervalDropdown\" method");
			
		} catch (InterruptedException e) {
			
			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Failed to fire time rage setting methods.");
			
		}			
				
		//Click "View Report" button
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableById("showReport", 120);
		WaitAndNotify.StartWait("Search For Test Machine > Click \"View Report\" button");
		
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();	
		
		//Releases a wait in Dashboard test
		WaitAndNotify.Notify("Search For Test Machine");
				
	};//SearchForTestMachine

}