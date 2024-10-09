package ProgramFiles.GloballyUsedClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;

public class MakeBrowsersList {

	public static Scanner sourceReader;
	public static boolean brwsFlag;
	public static ArrayList<String> browsersList = new ArrayList<>();
	public static String[] listStringArray;
	
	public static void ListSourceReader() {
	
		System.out.println("\n============> MAKE BROWSERS LIST <================\n");
		
		browsersList.add("Please select a browser"); // This produces a first line for the browsers menu

		try {
			
			ListBrowsers_.process_.destroy();
			File listSource = new File(System.getProperty("user.dir") + "\\src\\main\\java\\ProgramFiles\\ListBrowsers\\browsers_.txt");
			System.out.println(System.getProperty("user.dir") + "\\src\\main\\java\\ProgramFiles\\ListBrowsers\\browsers_.txt");

			sourceReader = new Scanner(listSource);
			
				while (sourceReader.hasNextLine()) {
					
					brwsFlag = true;
					String browserName = sourceReader.nextLine().toLowerCase();
					browsersList (browserName);
					
				}
				
				if (!sourceReader.hasNextLine()) {
					
					listStringArray(browsersList);
					sourceReader.close();
					WaitAndNotify.Notify("ListSourceReader");
					
				}//if
								
		} catch (FileNotFoundException e) {

			ProgramFiles.GloballyUsedClasses.OnTestFailure.OnTestFailureSystemExit(e, "Failed to read a browser list file. The test is shut down.");
			
		}//try
		
	}// ListSourceReader
	
	
	public static ArrayList<String> browsersList (String browserName_){
		
		if (browserName_.contains("irefox") || browserName_.contains("fire")) {
			browserName_ = "Firefox";
		} else if (browserName_.contains("hrome") || browserName_.contains("chr")) {
			browserName_ = "Chrome";
		} else if (browserName_.contains("xplorer") || browserName_.contains("iexplore")) {
			browserName_ = "Internet Explorer";
		} else if (browserName_.contains("dge") || browserName_.contains("Edge")) {
			browserName_ = "MS Edge";
		} else if (browserName_.contains("pera") || browserName_.contains("Opera")) {
			browserName_ = "Opera";
		} else if (browserName_.contains("Safari") || browserName_.contains("afari")) {
			browserName_ = "Safari";
		} else {
			browserName_ = "Unknown browser";
		}//eoif
			
		browsersList.add(browserName_);
		
		return browsersList;
		
	} // listStringArray
	
	
	public static String[] listStringArray (ArrayList<String> browsersList_) {
		
		listStringArray = new String[browsersList.size()];
		
		for (int i = 0; i < browsersList_.size(); i++) {
			listStringArray [i] = browsersList_.get(i);
		}
		
		System.out.println("####>>>>> " + Arrays.toString(listStringArray));
		
		try {
			ProgramFiles.GloballyUsedClasses.ComboStuff.comboBox(browsersList_);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return listStringArray ;
		
	}//listStringArray

}// class
