package ProgramFiles.GloballyUsedClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.jsoup.Jsoup;
import org.xml.sax.SAXException;

import ProgramFiles.GloballyUsedClasses.NetTrafficControl;

public class AppendScreenshotsToHTML {
	
	private static org.jsoup.nodes.Element containerDiv;

	public static org.jsoup.nodes.Element AppendNewDiv (String className) 
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		try {
		
			String pathToHTML = System.getProperty("user.dir") + "\\htmlReportsDir\\Screenshots.html";
			
			String pathToScreenShots = System.getProperty("user.dir") + "\\screenShots\\";
			
			System.out.println(" =========== ADD SCREENSHOTS TO HTML ========== ");

			// Opens a stream
			File reportFile=new File(pathToHTML);
			org.jsoup.nodes.Document doc= Jsoup.parse(reportFile , "utf-8" );
			
			//Creates node Element
			containerDiv=((org.jsoup.nodes.Element) doc).getElementById("container");
			containerDiv.append(
				"<div id=\""+className+"\">"
				+"<p class=\"sectionTitle\">"+OpenSelectedScreen.menuEntryName+"</p>"
				+"<p id=\"divSubtitle\">Click on an image to see it enlarged</p></div>"
			);//append
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
						
			//Creates node Element 2
			org.jsoup.nodes.Element sectionDiv=((org.jsoup.nodes.Element) doc).getElementById(className);
			
			//Reads screenshot file names in directory and inserts those screenshots into div
			File shotsFolder = new File(pathToScreenShots + className);
			File shotsList [] = shotsFolder.listFiles();
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));

			System.out.println("\n<<<<<<<<< shotsList [] length = " + shotsList.length + " >>>>>>>>>\n");
			
				for (File fileInDir : shotsList) {
					
					System.out.println(//Just for control
							"^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
							+ "fileInDir.getName()\n" + fileInDir.getName() 
							+ "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					//Appends screenshots to div
					sectionDiv.append("<p class=\"screenShotsParagraph\"><img class=\"screenShots\" src=\""
							+ pathToScreenShots + className + "\\" + fileInDir.getName() + "\" onclick=\"openEnlargedImg(this.src)\"></p>");
				
				};//for
				
			//Writes result into file and closes stream
			FileWriter fileWriter=new FileWriter(reportFile);
			fileWriter.write(doc.toString());
			fileWriter.flush();
			fileWriter.close();		
			
			TimeUnit.SECONDS.sleep((int) Math.round(NetTrafficControl.rateToInterval * 0.02 ));
			
			System.out.println(//Just for control
					"********************************************************\n"
					+ "From AppendNewDiv\n" + doc.toString() 
					+ "\n********************************************************");
						
			WaitAndNotify.Notify("ADD SCREENSHOTS TO HTML");
			
		} catch (InterruptedException e) {

			e.printStackTrace();
			
		} 
		
		return containerDiv;
		
	}; //AppendNewDiv
		
} // addScreenshotsToHTML
