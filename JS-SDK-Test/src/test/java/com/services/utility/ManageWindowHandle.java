package com.services.utility;

import java.util.Set;

import org.openqa.selenium.WebDriver;



public class ManageWindowHandle {
	
	public String switchToNewWindow(WebDriver driver) throws InterruptedException {
		ClickonFirstProduct clickfirst=new ClickonFirstProduct();
			
		String parentWindow=driver.getWindowHandle();
		String product_id = clickfirst.clickOnFabIndiaProduct(driver);
		Set<String> newWindows = driver.getWindowHandles();
		
		for(String childWindow : newWindows) {
			
			if(!parentWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				
			}
		}
		
		return product_id;
		
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
