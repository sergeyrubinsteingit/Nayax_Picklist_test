package ProgramFiles.GloballyUsedClasses;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;

public class CheckHTTPconnection {
	
	public static void ConnectionStatus (String httpPath) {
		
		System.out.println("<<<<<<<<<<<<<<<<<<< CONNECTION STATUS >>>>>>>>>>>>>>>>>>>");
		
		System.out.println("<<<<<<<<<<<<<<<< httpPath: "+httpPath+" >>>>>>>>>>>>>>>>");
		
		try {
			
			for (int i = 0; i < 10; i++) {
				
				URL url = new URL(httpPath);
				
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				
				connection.setRequestMethod("GET");
				
				connection.connect();

				LoginIntoDcs.connectionStatus = connection.getResponseCode();
				
				System.out.println("\n^^^^^^^^^^^^^^> Connection status code: "+ LoginIntoDcs.connectionStatus +" <^^^^^^^^^^^^^^\n");
								
				if (LoginIntoDcs.connectionStatus == 200) {
					
					WaitAndNotify.Notify("CONNECTION STATUS");
					
					break;
					
				};//if
				
				if (i >= 9) {
					
					WebDriverConfiguration.webDriver.quit();
					
					System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!> Connection failed <!!!!!!!!!!!!!!!!!!!!!!!!\n");
					
					System.exit(-1);
					
				};//if
				
				WebDriverConfiguration.webDriver.manage().timeouts().implicitlyWait( 20, TimeUnit.SECONDS);
				
			};//for
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	};//connectionStatus

}
