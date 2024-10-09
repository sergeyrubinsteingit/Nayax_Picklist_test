package ProgramFiles.GloballyUsedClasses;

import java.io.File;

public class MostRecentFileInDirectory {
	
	// to write a file here
	public static File selectedFile = null;
	
	public static File MostRecentFile () {
		
		System.out.println("<<<<<<< MOST RECENT FILE SEARCH BEGINS >>>>>>>");

		// path to directory
		String pathToDirectory = "C://Users/sergeyr/Downloads/";
		// directory settings
		File settingsForDirectory = new File(pathToDirectory);
		// listing files
		File [] fileList = settingsForDirectory.listFiles();
		// bigint to store last modified time
		long lastModifiedTime = Long.MIN_VALUE;
		
		if (fileList != null) {
			
			for (File file_ : fileList) {
				
				if (file_.lastModified() > lastModifiedTime) {
					
					selectedFile = file_;
					
					lastModifiedTime = file_.lastModified();
					
				} // if
				
			} // for
			
		}//if
		
		WaitAndNotify.Notify("MOST RECENT FILE SEARCH");
		
		return selectedFile;
		
	};// MostRecentFile

}
