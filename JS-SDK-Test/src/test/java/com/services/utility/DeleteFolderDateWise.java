package com.services.utility;

import java.io.File;

public class DeleteFolderDateWise {

	public static void main(String[] args) {
		String filePath = "/Users/samirsukla/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/Streamoid Projects/automation-scripts/JS-SDK-Test/Output";
		DeleteFolderDateWise del = new DeleteFolderDateWise();
		del.deleteFilesOlderThanNdays(4,filePath);

	}

	public void deleteFilesOlderThanNdays(final int daysBack, final String filePath) {

	   
		final File directory = new File(filePath);
	    if(directory.exists()){
	        System.out.println(" Directory Exists");
	        final File[] listFiles = directory.listFiles();          
	        final long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);

	        for(File listFile : listFiles) {
	            
	        	if(listFile.lastModified() < purgeTime) {
	                deleteFolder(listFile);

	            }
	        }
	    } 
	    else 
	    {
	    }
	}
	
	void deleteFolder(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteFolder(f);
	        }
	    }
	    file.delete();
	}
}