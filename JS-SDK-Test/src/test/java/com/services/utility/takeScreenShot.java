package com.services.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class takeScreenShot {
	static getSystemDate getDate;
	static CreateFolder createFolder;
	
	public void captureScreenShot(WebDriver driver,String currentDate,String folderName, String mainCat, String prodCat,String folderStatus,
			String product_id) throws IOException {
		String folderPath = "/home/streamoid/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/My First Project/JS-SDK-Test/Output/";
//		getDate = new getSystemDate();
		createFolder = new CreateFolder();
//		String date = getDate.getPresentDate();
		createFolder.createDirectory(currentDate, folderName);
		File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		if(folderStatus.equals("PASSED")){
		FileUtils.copyFile(scrfile,new File(folderPath+currentDate+"/"+folderName+"/"+"PASSED"+"/"+mainCat+"_"+prodCat+"_"+product_id+".jpeg"));
	}
		else {
			FileUtils.copyFile(scrfile,new File(folderPath+currentDate+"/"+folderName+"/"+"FAILED"+"/"+mainCat+"_"+prodCat+"_"+product_id+".jpeg"));
		}

		
	}

}
