package com.services.utility;

import java.util.Set;

import org.openqa.selenium.WebDriver;



public class ManageWindowHandle {
	
	public void switchToNewWindow(WebDriver driver) throws InterruptedException {
		clickonFirstProduct clickfirst=new clickonFirstProduct();
			
		String parentWindow=driver.getWindowHandle();
		clickfirst.clickOnFabIndiaProduct(driver);
		Set<String> newWindows = driver.getWindowHandles();
		
		for(String childWindow : newWindows) {
			
			if(!parentWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				
			}
		}
		
		
		
	}
	
	public void switchToParentWindow(WebDriver driver) throws InterruptedException {
		
		String parentWindow = driver.getWindowHandle();
		
		Set<String> newWindows = driver.getWindowHandles();
		
		for(String childWindow : newWindows) {
			
			if(!parentWindow.equalsIgnoreCase(childWindow)) {
				driver.close();
				driver.switchTo().window(childWindow);
				
			}
		}
		
		
		
	}

}
