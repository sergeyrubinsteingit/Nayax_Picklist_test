package ProgramFiles.GloballyUsedClasses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


public class CreateHtmlReportFiles {
	
	static String htmlReportsDir = (System.getProperty("user.dir") + "\\htmlReportsDir\\");
	static String [] reportFiles = {"Screenshots.html","Extentreport.html"};
	static File reportFile = null;
	
	public static String pathToHTMLfiles = null;
	
	public static BufferedWriter bufferedWriter;
	
	
	public static void createReportFiles () {
		
		System.out.println("<<<<<<<<<<<<< CREATE HTML FILES >>>>>>>>>>>>>");

		try {
			
			//Listing of the report files in directory
			System.out.println("Files in htmlReportsDir:\n");
			
			for ( Path htmlFilesPath : Files.newDirectoryStream(Paths.get(htmlReportsDir)) ) {
				
				System.out.println(htmlFilesPath.normalize().getFileName());
				
			} //for
			
			//Creating new files, deleting the former ones if they exist.
			for (String reportFile_ : reportFiles) {
				
				reportFile = new File(System.getProperty("user.dir") + "\\htmlReportsDir\\" + reportFile_);//
				
					if (reportFile.exists()) {
						
						System.out.println("<<<<<<<<<<<<< Former " + reportFile.getName().toUpperCase().toString() + " is DELETED. >>>>>>>>>>>>>");
						reportFile.delete();
						
					};//if			
					
				reportFile.createNewFile();
				
				System.out.println("<<<<<<<<<<<<< New " + reportFile.getName().toUpperCase().toString() + " is CREATED. >>>>>>>>>>>>>");
			
			} //for
			
			//Checks if the file exists, then fires createHtmlOpeningTags(), otherwise shuts the test down
			for (int i = 0; i < 5; i++) {
				
				if (reportFile.exists()) {
					
					createHtmlOpeningTags();
					
					break;
					
				} else if (i == 4 && !reportFile.exists()) {
					
					Exception e = new Exception("Report file " + reportFile.getName().toString() + " is not found.");
					
					ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Report file is not found. The test is shut down.");
				
				} else {
					
					TimeUnit.SECONDS.sleep(1);
					
				} //if
				
			} //for
			
		} catch (IOException | InterruptedException e) {
			
			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Report file is not found. The test is shut down.");

		} //try
		
		// releases the thread
		WaitAndNotify.Notify("Create Report Files");
		
	} //createReportFiles
	
	
	// Adds opening tags to HTML report file
	public static BufferedWriter createHtmlOpeningTags () {
		
		try {
			
			String pathToJsCssFiles = System.getProperty("user.dir").toString() + "\\src\\main\\java\\ProgramFiles\\JsCss\\";
			
			pathToHTMLfiles = System.getProperty("user.dir") + "\\htmlReportsDir\\" + reportFiles[0];
						
			String timestamp_ = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

			bufferedWriter = new BufferedWriter(new FileWriter(pathToHTMLfiles));
			
			bufferedWriter.write("<html><head><meta charset='utf-8'/>");
			bufferedWriter.write("<link rel=\"stylesheet\" href=\"" + pathToJsCssFiles + "TestReportStyles.css\">");
            // a function to open enlarged image in new popup window
            bufferedWriter.write("<script language=\"javascript\">");
            bufferedWriter.write("function EnlargeImage(img_src){");
            bufferedWriter.write("ScreenshotWindow = window.open('','Test Screenshot','width=900px, height=750px,scrollbars=yes');");
            bufferedWriter.write("ScreenshotWindow.document.write('<html><head><link rel=\"stylesheet\" href=" + pathToJsCssFiles + "TestReportStyles.css\"></head>" 
                                + "<body style=\"background-color:#434343; \">" 
                                + "<input type=\"button\" width=\"150px\" height=\"50px\" onClick=\"javascript:window.close();\" value=\"Close the window\""
                                + "style=\"position:absolute;top:15px;left:15px;background-color:#ffcd00;z-index:5;border: 0px solid #fff;border-radius:25px;cursor:pointer; \">"
                                + "<div style=\"position:absolute;top:0;left:0;width:100%;height:100%;\">"
                                + "<img id=\"EnlargedImage\" src=' + img_src + ' style=\"position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);width:90%;height:auto;"
                                + "display:inline-block;z-index:10;padding: 5px 5px 5px 5px;\"></div></body></html>');");
            bufferedWriter.write("} // end of function");
            bufferedWriter.write("</script>");
            //
            bufferedWriter.write("</head><body style=\"background - color:#434343; \">");
            bufferedWriter.write("<br><div id=\"HeaderDiv\"><h2 id=\"reportHeader\">TEST REPORT at " + timestamp_ + "</h2></div>");
            bufferedWriter.write("<div id=\"container\">");

		} catch (IOException e) {
			
			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Failed to add basic HTML to screen shot reports file.");

		} //try
		
		// releases the thread
		WaitAndNotify.Notify("Create Html Opening Tags");
		
		return bufferedWriter;
		
	};// createHtmlOpeningTags
	
	
	// This part appends new block to HTML report after each test
    static String ScreenShotsPath = "C:\\Users\\sergeyr\\eclipse2\\NayaxTestSet\\screenShots\\";
	private static String TestReportFontColor;
	private static String TestResultDescription;
	

	public static void AppendBlockToHtml(String ScreenShotSource, String TestRecord, boolean elementStateForTestReports) throws IOException {
		
		if (elementStateForTestReports) {
			
			TestReportFontColor = "#94f736";  // Green
			
			TestResultDescription = " test passed.";
			
		} else {
			
			TestReportFontColor = "#f77036";  // Red
			
			TestResultDescription = " test failed.";
			
		} //if 

    	bufferedWriter.write("<div class=\"TestRecordsCollection\">");
    	bufferedWriter.write("<div class=\"PictureHolder\">" + ScreenShotSource + "</div>");
    	bufferedWriter.write("<div class=\"TestRecord\"><span class=\"TestRecordSpan\" style=\"color:" + TestReportFontColor + ";\">" + TestRecord + TestResultDescription + "</span></div>");
    	bufferedWriter.write("</div>");   	
    	
    	// releases the thread
    	WaitAndNotify.Notify("Append Block To Html");

    }// AppendBlockToHtml
	
	
    // closes HTML report file
    public static void CreateHtmlClosingTags() throws IOException {
    	
        // Last tags
    	bufferedWriter.write("</div>");
    	bufferedWriter.write("</body></html>");
    	bufferedWriter.close();    	
    	
    	// releases the thread
    	WaitAndNotify.Notify("Create Html Closing Tags");
    	
    }; // CreateHtmlClosingTags
	
}; // CreateHtmlReportFiles
