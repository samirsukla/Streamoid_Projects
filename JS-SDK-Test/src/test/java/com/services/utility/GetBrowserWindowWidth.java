package com.services.utility;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class GetBrowserWindowWidth {

	
	
  public int getWebPageWidth(WebDriver driver) {
	 
	 Dimension d = driver.manage().window().getSize();
	 int browserWidth = d.getWidth();
	// int browserHeight = d.getHeight();
	 
		return browserWidth;
		
  }
}
