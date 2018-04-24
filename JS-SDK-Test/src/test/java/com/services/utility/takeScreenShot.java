package com.services.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class takeScreenShot {
	static getSystemDate getDate;
	
	public void captureScreenShot(WebDriver driver,String className) throws IOException {
		
		getDate = new getSystemDate();
		String currentDate = getDate.getPresentDate();
		File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrfile,new File("/home/streamoid/Pictures/AllenSolly_Women_shirts&blouses.png"));

		
	}

}
