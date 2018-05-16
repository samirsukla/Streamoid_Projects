package com.services.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class InitialSetup {
	static Properties props;
	static FileInputStream finput;
	static WebDriver driver;
	
	public WebDriver initialSetup() throws IOException {
		
		System.setProperty("webdriver.gecko.driver", "/home/streamoid/geckodriver");
		FirefoxProfile fprofile = new FirefoxProfile();
		fprofile.setPreference("dom.webnotifications.enabled", false);
		fprofile.setPreference("dom.push.enabled", false);
		FirefoxOptions option = new FirefoxOptions(); 
		option.setProfile(fprofile);
		driver = new FirefoxDriver(option);
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