package com.services.utility;

import java.io.File;

public class CreateFolder {
	
	public void createDirectory(String date_folder, String folder_name) {
	
		String path ="/Users/samirsukla/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/Streamoid Projects/automation-scripts/JS-SDK-Test/Output/";
		File dir = new File(path+ date_folder+ "/"+folder_name);
		if(!dir.exists()) {
		  dir.mkdir();
		  File passFolder = new File(path+date_folder+"/"+folder_name+"/"+"PASSED");
		  File failFolder = new File(path+date_folder+"/"+folder_name+"/"+"FAILED");
		  passFolder.mkdir();
		  failFolder.mkdir();
		}
		
		
	}
	
	public void createDateDirectory(String date_folder) {
		
		String path ="/Users/samirsukla/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/Streamoid Projects/automation-scripts/JS-SDK-Test/Output/";
		File dir = new File(path+"/"+date_folder);
		if(!dir.exists()) {
		  dir.mkdir();
		}
		
		
	}

}
