package ProgramFiles.GloballyUsedClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import ProgramFiles.TestUsedClasses.LoginIntoDcs;
import ProgramFiles.TestUsedClasses.MachinesProductMap;

public class PdfReader {
	
	public static boolean isPassed;
	
	public static String picklistResume;
	
	public static String finalMessage;
		
	
	public static void ReadPdf (String selectedFile_, boolean allSelections_) throws IOException {
		
		System.out.println("<<<<<<< READ PDF BEGINS >>>>>>>");
		
		String isAllSelectionsOn = allSelections_? "with all selections option on" : "";
		
		com.Start.TestProcedure.screenName = allSelections_? "Picklist with all selections" : "Picklist with picks only";
		
		String rowsInMapToPrint = allSelections_? String.valueOf(MachinesProductMap.totalRows) : String.valueOf(MachinesProductMap.rowsHavingPicks);
				
		File fileToRead = new File(selectedFile_);
		
		// loads the pdf for reading
		PDDocument pdDocument = PDDocument.load(fileToRead);
		
		// extracts a text
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		
		// reads page 2 of the file
		pdfTextStripper.setStartPage(2);
		pdfTextStripper.setEndPage(2);
		
		// places all the lines into a string
		String documentTextAsString = pdfTextStripper.getText(pdDocument).trim();
		
		// splits the string by the new line marks and stores in array
		String [] lineByLineTextArray = documentTextAsString.split("\r\n|\r|\n");
		
		// index of the element two items down from which lays a first element to be written to list for a validation
		int firstElementToFindInList = Arrays.asList(lineByLineTextArray).indexOf("Refunds:");
		
		// adds bins to list. No option exists to remove items from string array so the list is needed.
		List<String> convertedToArrayList = new ArrayList<String>();
		
		// a timestamp
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date date_ = new Date();
		
		System.out.println("\nThe PDF file [" + ProgramFiles.GloballyUsedClasses.MostRecentFileInDirectory.selectedFile.getAbsoluteFile().toString() + "] is read at: ".toUpperCase() + dateFormatter.format(date_) );
		
		for (int i = firstElementToFindInList + 2; i < lineByLineTextArray.length; i++) {
						
			convertedToArrayList.add(lineByLineTextArray[i]);
			
		} //for
		
		System.out.println("\n<<<<<<< PDF CONTENTS IN " + com.Start.TestProcedure.screenName + " >>>>>>>");
		for (String record_ : convertedToArrayList) {
			
			System.out.println("PDF Record: " + record_);
									
		} //for
				
		// Comparison between number of rows in the map and in the PDF
		if (Integer.valueOf(rowsInMapToPrint) == convertedToArrayList.size()) {
			
			picklistResume = "Correct picklist content. ";
			
			com.Start.TestProcedure.elementStateForTestReports = true;
			
		} else {
			
			picklistResume = "Incorrect picklist content. ";
			
			com.Start.TestProcedure.elementStateForTestReports = false;
			
		}//if
		
		// Final message
		finalMessage = picklistResume + "Number of rows " + isAllSelectionsOn 
				+ ": in the Map to be printed:" + rowsInMapToPrint + ", in the PDF file: " + convertedToArrayList.size();
		
		System.out.println("\n" + finalMessage);
		
		// closes the PDF
		pdDocument.close();
		
		WaitAndNotify.Notify("from PDF Reader");
		
	}// ReadPdf

}//PdfReader
