package ProgramFiles.GloballyUsedClasses;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import org.openqa.selenium.WebDriver;


public class ListBrowsers_ {
		
	public static String listBrowsersPath = "\\src\\main\\java\\ProgramFiles\\ListBrowsers\\";
	public static WebDriver webDriver;
	public static String [] batchFileName = {"browsers_list.bat", "brows_Lst_.bat"} ;
	public static Process process_;
	
	
	public static void RedefineBrowsersList() {
		
		String browsLogDir = System.getProperty("user.dir") + listBrowsersPath;
		
		File browsLogFile = new File(browsLogDir + "browsers_.txt");
		
		try {
			System.out.println("<<<<<<<<<<<<< LIST BROWSERS >>>>>>>>>>>>>");

			if (browsLogFile.exists()) {
				
				browsLogFile.delete();
				
				System.out.println("Browsers list file is DELETED");
				
			} //if
			
			browsLogFile.createNewFile();
			
			System.out.println("Browsers list file is CREATED");
			
		} catch (IOException e) {
			
			System.out.println("Browsers list file FAILED");
			
			System.exit(-1);
			
		} // try
		
		try {
			File browserNamesLog = new File(System.getProperty("user.dir") + listBrowsersPath + "browsers_.txt");

			ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + listBrowsersPath + batchFileName[0]);			    
			processBuilder.directory(new File(System.getProperty("user.dir") + listBrowsersPath));				
			processBuilder.redirectErrorStream(true);
			processBuilder.redirectOutput(Redirect.appendTo(browserNamesLog));
			
			process_ = processBuilder.start();
			process_.waitFor();
			
			System.out.println("<<<<<<<<<<<<<< Process Builder SUCCEEDED >>>>>>>>>>>>>");
			
			System.gc();
			
		} catch (IOException | InterruptedException e) {
			
			System.out.println("<<<<<<<<<<<<<< Process Builder FAILED >>>>>>>>>>>>>\n");
			
			e.printStackTrace();
			
		}//try
		
		// releases the wait
		WaitAndNotify.Notify("Redefining a list of browsers");

	}// renewLogFile

}//eoclass
