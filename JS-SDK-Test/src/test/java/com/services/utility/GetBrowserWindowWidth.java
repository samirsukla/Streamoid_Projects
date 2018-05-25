package com.services.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class GetBrowserWindowWidth {
public 	WebDriver driver;
  @Test
  public void getWebPageWidth() {
	  
	  System.setProperty("webdriver.gecko.driver", "/home/streamoid/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://www.pothys.com");
		driver.manage().window().maximize();
		System.out.println(driver.manage().window().getSize());
		
  }
}
