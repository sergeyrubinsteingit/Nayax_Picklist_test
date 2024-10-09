package ProgramFiles.GloballyUsedClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverConfiguration {	
	
	public static String driverPath;
	public static WebDriver webDriver;
	public static String driverType;
	
	
	public static WebDriver webDriverSettings () throws InterruptedException {
		
		driverPath = "\\src\\main\\java\\web_drivers_";

		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
				
		//Sets selected browser
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< " + ComboBoxBuilder.selectedBrowser + " >>>>>>>>>>>>>>>>>>>>>>>>");

		TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
		
		//Browser settings:		
		switch (ComboBoxBuilder.selectedBrowser) {
		
			case "Firefox":
				
				System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + driverPath + "\\geckodriver.exe");
				DesiredCapabilities capabilities;
				capabilities = new DesiredCapabilities();
				capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				capabilities.setCapability("marionette", true);
				webDriver = new FirefoxDriver(capabilities);
				
				break;
				
			case "Chrome":
					
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +  driverPath + "\\chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
				webDriver = new ChromeDriver(chromeOptions);
					
				break;	
				
			case "MS Edge":
					
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +  driverPath + "\\msedgedriver.exe");
				webDriver = new EdgeDriver();
					
				break;
				
			case "Internet Explorer":
					
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +  driverPath + "\\IEDriverServer.exe");
				InternetExplorerOptions ie_options = new InternetExplorerOptions().enableNativeEvents().ignoreZoomSettings();
				webDriver = new InternetExplorerDriver(ie_options);
					
				break;
				
			case "Opera":
				
				System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") +  driverPath + "\\operadriver.exe");
				webDriver = new OperaDriver();
					
				break;
				
			case "Safari":
				
				webDriver = new SafariDriver();
					
				break;
	
					
			default:
				
				System.out.println("\n<<<<<<<<<<<<<<<<<< FAILURE: WEB DRIVER WAS NOT FOUND >>>>>>>>>>>>>>>>>>");
				System.exit(-1);
				
				break;
			
		}//switch
		
		// thread release
		WaitAndNotify.Notify("Web driver configuration ");
		
		return webDriver;
		
	} // webDriverSettings

} // WebDriverConfiguration
