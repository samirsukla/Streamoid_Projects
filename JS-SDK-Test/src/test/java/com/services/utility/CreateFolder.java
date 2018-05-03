package com.services.utility;

import java.io.File;

public class CreateFolder {
	
	public void createDirectory(String parent_folder, String folder_name) {
	
		String path ="/home/streamoid/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/My First Project/JS-SDK-Test/Output/";
		File dir = new File(path+ parent_folder+ "/"+folder_name);
		if(!dir.exists()) {
		  dir.mkdir();
		}
		
		
	}

}
