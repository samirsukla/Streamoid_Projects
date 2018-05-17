package com.services.utility;

import java.io.IOException;


import org.openqa.selenium.WebDriver;

public class CheckWidgetPresentStatus {
	
	
	public void checkStatusAndTakeScreenshot(WebDriver driver,String currentDate,String folderName,String mainCategoryName, String 
			prodCategoryName,String status,String product_id,boolean isDisplaying) throws IOException{
		takeScreenShot scrshot;
		
		if(status=="passed" && isDisplaying== true) {
			
			String folderStatus = "PASSED";
			scrshot = new takeScreenShot();
			scrshot.captureScreenShot(driver,currentDate,folderName,mainCategoryName,prodCategoryName,folderStatus,product_id);
		}else {
			String folderStatus = "FAILED";
			scrshot = new takeScreenShot();
			scrshot.captureScreenShot(driver,currentDate,folderName,mainCategoryName,prodCategoryName,folderStatus,product_id);
		}
	}
	
	
}
