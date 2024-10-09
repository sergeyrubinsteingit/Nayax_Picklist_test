package ProgramFiles.TestUsedClasses;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import ProgramFiles.GloballyUsedClasses.NetTrafficControl;
import ProgramFiles.GloballyUsedClasses.TestDataStorage;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;

public class MomaOrSmsLogin {
	
	public static void MomaOrSms () throws InterruptedException {
				
		System.out.println("\n<<<<<<<<<<< MOMA / SMS AUTHENTICATIION >>>>>>>>>>>>\n");
		
		try {

			//Attempt to find login field of SMS version
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableById(TestDataStorage.SMS_LOGIN_FIELD_, 15);
			WaitAndNotify.StartWait("Moma or sms > Attempt to find login field of SMS version");
			
			System.out.println("\n<<<<<<<<<<< TRY 1 : WEBELEMENT = " + ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getAttribute("id") +  " >>>>>>>>>>>>\n");
			
		} catch (Exception e1) {
			
			try {

				//Attempt to find login field of MOMA version
				ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableById(TestDataStorage.MOMA_LOGIN_FIELD_ , 15);
				WaitAndNotify.StartWait("Moma or sms > Attempt to find login field of MOMA version");
				
				System.out.println("\n<<<<<<<<<<< TRY 2 : WEBELEMENT = " + ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getAttribute("id") +  " >>>>>>>>>>>>\n");
				
			} catch (Exception e2) {
				
				ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e2, "Failed to pass in Moma Authentication. The test is shut down.");
				
			}//try				
		
		}//try
		
		System.out.println("\n<<<<<<<<<<< AFTER TRY : WEBELEMENT = " + ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.getAttribute("id") +  " >>>>>>>>>>>>\n");
				
		//Open the dialog to pass SMS to system
		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
		String verCodeString = JOptionPane.showInputDialog("Verification code:","Enter here");
		ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.sendKeys(verCodeString);
		
		//Searches for the button
		try {

			// Click Sign in button
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableById(TestDataStorage.SIGNIN_BUTTON_, 120);
			WaitAndNotify.StartWait("Moma or sms > Click Signin button");			
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
			
			//Click Not Now link
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ElementToBeClickableById(TestDataStorage.NOT_NOW_LINK_, 120);
			WaitAndNotify.StartWait("Moma or sms > Click Not Now link");			
			ProgramFiles.GloballyUsedClasses.WaitForExpectedCondition.ExpectedElement.click();
			
			WaitAndNotify.Notify("Notify from Moma Or Sms Authentication");
			
		} catch (Exception e3) {
						
			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e3, "Failed to find a sign in button. The test is shut down.");
			
		}//try
						
	};//MomaOrSms

}//ifMomaAuthentication

///"nayax-spinner"

