//This method initiates TestNG library
//and starts testing process running file testng.xml.
//Testng.xml sets testing parameters.
//List testSuites can be useful in case
//of multiply .xml files applied.

package ProgramFiles.GloballyUsedClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import com.beust.jcommander.internal.Lists;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;


public class RunTestNG {
	
	public static WebDriver webDriver;
	
	public static void TestNgRun() throws InterruptedException {
		
		System.out.println("<<<<<<<<<<<<< RUN TEST NG >>>>>>>>>>>>>");
		
		webDriver = WebDriverConfiguration.webDriver;
		
		TestNG newTestNG = new TestNG();
		
		List<String> testSuites = Lists.newArrayList();
		
		testSuites.add(System.getProperty("user.dir") + "\\src\\main\\java\\ProgramFiles\\GloballyUsedClasses\\testng.xml");
		
		webDriver.manage().timeouts().implicitlyWait( 20, TimeUnit.SECONDS);
		
		newTestNG.setTestSuites(testSuites);
		
		newTestNG.run();
		
		WaitAndNotify.Notify("RUN TEST NG");
		
	} // runTestNg
	
} //eoclass
