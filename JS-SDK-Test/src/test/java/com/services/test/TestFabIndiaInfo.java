package com.services.test;

import org.testng.annotations.Test;

import com.services.utility.clickonFirstProduct;
import com.services.utility.takeScreenShot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestFabIndiaInfo {
	
	public WebDriver driver;
	static clickonFirstProduct clickfirst;
	static takeScreenShot scrshot;
	static Properties props;
	static FileInputStream finput;
	
	
	
	@BeforeClass
	public void setUp() throws IOException {
		System.setProperty("webdriver.gecko.driver", "/home/streamoid/geckodriver");
		driver = new FirefoxDriver();
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		props = new Properties();
		finput = new FileInputStream("src/test/resources/URLInfo.properties");
		props.load(finput);
		clickfirst = new clickonFirstProduct();
		
		
	}
	@Test
	public void testFabIndia() throws IOException, InterruptedException {
		
		String url = props.getProperty("Fab_India");
		driver.get(url);
		
		
		
		WebElement main_category = driver.findElement(By.xpath(".//*[@id='sample-menu-2']/li[2]/a"));
		List<WebElement> sub_category_list = driver.findElements(By.xpath(".//*[@id='sub_floor_1']/div/div/div/div/div/div/div[1]/div[1]/dl/dd/ol/li"));
		int sub_category_list_size = sub_category_list.size();
		
		
		
		for(int i=1;i<=sub_category_list_size; i++) {
			
		clickfirst.moveToElement_only(main_category, driver);
		WebElement sub_category = driver.findElement(By.xpath(".//*[@id='sub_floor_1']/div/div/div/div/div/div/div[1]/div[1]/dl/dd/ol/li["+i+"]/a"));
		clickfirst.moveToElementandClick(main_category, sub_category, driver);
		Thread.sleep(5000);
		}
		
		}
	
		
		
		
		
	
	@AfterClass
	public void tearDown() {
		
		driver.close();
	}

}
