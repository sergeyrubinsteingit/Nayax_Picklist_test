package ProgramFiles;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentRepHTMLsetting {
	
	//ExtentReports settings
	public static ExtentHtmlReporter extentHtmlReporter;
	public static ExtentReports extentReports;
	
	static String pathToHTML = System.getProperty("user.dir") + "\\htmlReportsDir\\Extentreport.html";
	
	public static ExtentReports ExtentRepHtml () {
		
		File logFile = new File(pathToHTML);
		//Adding extentReports properties to instance of the extentHtmlReporter
		extentReports = new ExtentReports();
		extentReports = null;
			if (extentReports == null) {
				extentHtmlReporter = new ExtentHtmlReporter(logFile);
			};//if
			
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		
		//Enables new additions to existing ExtentHtmlReporter
		extentHtmlReporter.setAppendExisting(true);
		extentHtmlReporter.start();
		
		//ExtentHtmlReporter configuration
		extentHtmlReporter.config().getChartVisibilityOnOpen();
		extentHtmlReporter.config().setFilePath(pathToHTML);
		extentHtmlReporter.config().setDocumentTitle("Test statistic report");
		extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		extentHtmlReporter.config().setProtocol(Protocol.HTTPS);
		extentHtmlReporter.config().setTheme(Theme.STANDARD);
		extentHtmlReporter.config().setEncoding("utf-8");
		extentHtmlReporter.config().setReportName("Statistic report");
		extentHtmlReporter.config().setJS("js-string");
		
		// Settings fro the extentReports environment
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Browser", "Chrome");
		
		return extentReports;		
	}
}
