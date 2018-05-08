package com.services.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitialSetup {
	static Properties props;
	static FileInputStream finput;
	static WebDriver driver;
	
	public WebDriver initialSetup() throws IOException {
		
		System.setProperty("webdriver.gecko.driver", "/home/streamoid/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		props = new Properties();
		finput = new FileInputStream("src/test/resources/URLInfo.properties");
		props.load(finput);
		return driver;
		
	}
	
	public String getUrl(String urlFromProFIle) {
		String url = props.getProperty(urlFromProFIle);
		return url;
		
	}

}