package ProgramFiles.GloballyUsedClasses;

import org.openqa.selenium.WebDriver;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;

public class WaitAndNotify {
	
//	static boolean awaitTestFinish = OpenSelectedScreen.awaitTestFinish = true;
//	static Object objectForSynch = OpenSelectedScreen.objectForSynch = new Object();
	static boolean awaitTestFinish= true;
	static Object objectForSynch = new Object();
	static WebDriver webDriver = WebDriverConfiguration.webDriver;

	public static void StartWait(String StartWaitFromMethod) {
		System.out.println(" =========== StartWait from Wait And Notify, invoked from "+StartWaitFromMethod.toUpperCase()+" ========== ");
		synchronized (objectForSynch) {
			while (awaitTestFinish) {
	            try {
	            	objectForSynch.wait();
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt(); 
	                System.out.println("Thread Interrupted");
	                webDriver.quit();
					System.exit(-1);
	            }//try
	        }//while
	        awaitTestFinish = true;
		}//synchronized
	}//StartWait
	
	public static void Notify(String NotifyAllFromMethod) {
		System.out.println(" =========== Notify from Wait And Notify, invoked from "+NotifyAllFromMethod.toUpperCase()+" ========== ");
		synchronized (objectForSynch) {
			try {
				awaitTestFinish = false;			        
//				objectForSynch.notifyAll();
				objectForSynch.notify();
			} catch (Exception e) {
				e.printStackTrace();
				webDriver.quit();
				System.exit(-1);
			}
		};//synchronized
	}// NotifyAll
	
	public static void NotifyAll(String NotifyAllFromMethod) {
		System.out.println(" =========== NotifyAll from Wait And Notify, invoked from "+NotifyAllFromMethod.toUpperCase()+" ========== ");
		synchronized (objectForSynch) {
			try {
				awaitTestFinish = false;			        
				objectForSynch.notifyAll();
			} catch (Exception e) {
				e.printStackTrace();
				webDriver.quit();
				System.exit(-1);
			}
		};//synchronized
	}// NotifyAll
	
}///////////////////////////////////////////////////////////////////////////////
