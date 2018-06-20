package com.services.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot {
	static GetSystemDate getDate;
	static CreateFolder createFolder;
	static CreateAbsolutePath createabs;
	
	public void captureScreenShot(WebDriver driver,String currentDate,String folderName, String mainCat, String prodCat,String folderStatus,
			String product_id) throws IOException {
		createabs=new CreateAbsolutePath();
		String folderPath = createabs.makeAbsolutePath();
		createFolder = new CreateFolder();

		createFolder.createDirectory(currentDate, folderName);
		File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		if(folderStatus.equals("PASSED")){
		FileUtils.copyFile(scrfile,new File(folderPath+"/"+currentDate+"/"+folderName+"/"+"PASSED"+"/"+mainCat+"_"+prodCat+"_"+product_id+".jpeg"));
	}
		else {
			FileUtils.copyFile(scrfile,new File(folderPath+"/"+currentDate+"/"+folderName+"/"+"FAILED"+"/"+mainCat+"_"+prodCat+"_"+product_id+".jpeg"));
		}

		
	}

}
