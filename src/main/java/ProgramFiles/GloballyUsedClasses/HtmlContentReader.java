package ProgramFiles.GloballyUsedClasses;

import java.io.BufferedReader;
import java.io.FileReader;


public class HtmlContentReader {
	static String Extentreporthtml = (System.getProperty("user.dir") + "\\htmlReportsDir\\Extentreport.html");

	public static void HtmlReader(String invokedFromLabel) {
		
		System.out.println(" **=========== HTML CONTENT READER ==========** ");

		try {
			StringBuilder stringBuilder = new StringBuilder();//To write reading file results into
			FileReader fileReader = new FileReader (Extentreporthtml);
			
			BufferedReader bufferedReader = new BufferedReader (fileReader);//Copying into from File  reader
			
			String controlString;
			
				while ( (controlString = bufferedReader.readLine()) != null) {
					stringBuilder.append(controlString);
				};//while
				
			System.out.println("Reading Extentreport.html after " +invokedFromLabel+ ":\n" + stringBuilder.toString() + "\n");
			
			bufferedReader.close();
		}catch (Exception e){
			
		};//try
	}

}
