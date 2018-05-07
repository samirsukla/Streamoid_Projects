package com.services.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.services.utility.clickonFirstProduct;

public class TestGlobusFashionInfo {
	
	public WebDriver driver;
	static Properties props;
	static FileInputStream finput;
	static String className;
	static String folderName;
	static clickonFirstProduct clickfirst;
	static String mainCategory;
	
	
	@BeforeClass
	public void setUp() throws IOException {
		
		System.setProperty("webdriver.gecko.driver", "/home/streamoid/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		props = new Properties();
		finput = new FileInputStream("src/test/resources/URLInfo.properties");
		props.load(finput);
		clickfirst = new clickonFirstProduct();
		className = this.getClass().getSimpleName();
		folderName = className.replace("Test", " ").replace("Info", " ").trim();
		
	}
  @Test
  public void testGlobusFashion() throws Exception {
	  
	  String url = props.getProperty("Globus_Fashion");
	  driver.get(url);
	  
	  
		for(int i=1;i<=4; i++) {
		
		mainCategory = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/a/span")).getText();
		
		int j=1;
		List<WebElement> sub_categ_div = driver.findElements(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/div/div[1]/ul/li"));
		
		for(int y=1; y<=sub_categ_div.size(); y++) {
			WebElement product_category = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/a/span"));
			clickfirst.moveToElement_only(product_category, driver);
			try {
				while(driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/div/div[1]/ul/li["+y+"]/div/div/ul/li["+j+"]/a/span")).isDisplayed()) {
					
					WebElement main_Category = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/a/span"));
					WebElement product_category1 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/div/div[1]/ul/li["+y+"]/div/div/ul/li["+j+"]/a/span"));
					String prodCategory = clickfirst.getCategoryName(product_category1);
					
					clickfirst.moveToElementandClick(product_category, product_category1, driver);
					
                    
					clickfirst.clickOnGlobusProduct(driver);
					
				
					
					Thread.sleep(2000);
					
//					scrshot = new takeScreenShot();
//					scrshot.captureScreenShot(driver,folderName,mainCategory,prodCategory);

					j++;

					driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/div/div[3]/div/h1/a/img")).click();
					WebElement main_category = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/a/span"));
					clickfirst.moveToElement_only(main_category, driver);
					
				}
					
				}
			catch(Exception e) {
				
				j=1;
				
			}
		}
		}
	  
  }
  
  
  @AfterClass
  public void tearDown() {
	  
	  driver.close();
  }
}
