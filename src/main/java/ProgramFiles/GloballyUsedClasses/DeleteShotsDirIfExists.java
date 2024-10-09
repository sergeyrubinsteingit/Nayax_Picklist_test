package ProgramFiles.GloballyUsedClasses;

import java.io.File;

public class DeleteShotsDirIfExists {
	
	//Delete dir if it exists
	public static boolean DeleteDirIfExists (File screenshotsDir) {
				
		if (screenshotsDir.isDirectory()) {
			
			String [] FilesInDirectory = screenshotsDir.list();
			
			for (int i = 0; i < FilesInDirectory.length; i++) {
				
				boolean IsSuccessfullyDeleted = DeleteDirIfExists (new File (screenshotsDir, FilesInDirectory[i]));
				
				if (!IsSuccessfullyDeleted) {
					return false;
				};//if
				
			};//for
			
		};//if
		
			// creates dir anew
			CreateShotsDir(screenshotsDir);
			WaitAndNotify.StartWait("Create Shots Dir");
		
			WaitAndNotify.Notify("Delete Dir If Exists");
			
			return screenshotsDir.delete();
		
		};//DeleteDirIfExists
		
		
		// create dir
		public static boolean CreateShotsDir (File screenshotsDir) {
					
			if (!screenshotsDir.exists()) {
				
				screenshotsDir.mkdir();
				
			};//if
			
				WaitAndNotify.Notify("Create dir");
				
				return screenshotsDir.mkdir();
			
			};//DeleteDirIfExists

}
