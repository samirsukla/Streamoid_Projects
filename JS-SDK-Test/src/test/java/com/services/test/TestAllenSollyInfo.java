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

public class TestAllenSollyInfo {
	
	public WebDriver driver;
	static clickonFirstProduct clickfirst;
	static takeScreenShot scrshot;
	static Properties props;
	static FileInputStream finput;
	static int z=0;
	
	
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
	public void testAllenSolly() throws IOException, InterruptedException {
		
		String url = props.getProperty("Allen_Solly");
		driver.get(url);
		
		List<WebElement> main_category_list = driver.findElements(By.xpath("//ul[@id='nav-bar']/li"));
		int main_category_size = main_category_list.size();
		//System.out.println(main_category_list);
		
		for(int i=1;i<=main_category_size; i++) {
			
		int j=1;
		List<WebElement> child_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div"));
		
		for(int x=1; x<=child_categ_div.size(); x++) {
			
			List<WebElement> sub_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div[1]/div"));
			WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
			clickfirst.moveToElement_only(product_category, driver);
			for(int y=1;y<=sub_categ_div.size(); y++) {
				
				try {
					while(driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div["+y+"]/ul/li["+j+"]/a")).isDisplayed()) {
						WebElement sub_Category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
						WebElement product_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div["+y+"]/ul/li["+j+"]/a"));
						clickfirst.moveToElementandClick(sub_Category, product_category1, driver);
						//clickfirst.clickOnCategory(main_category_list.get(z),product_category,driver);
						clickfirst.clickOnProduct(driver);
		//				Thread.sleep(3000);
						j++;
		//				product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div["+y+"]/ul/li["+j+"]/a"));
						driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[2]/div[1]/a/img")).click();
						WebElement element1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
						clickfirst.moveToElement_only(element1, driver);
						
					}
					}
					catch(Exception e) {
						e.printStackTrace();
						System.out.println("No link exist");
						j=1;
					}
			}
		}
		z++;
		}
	
		
		
		/*
		
		String className = this.getClass().getSimpleName();
		scrshot = new takeScreenShot();
		scrshot.captureScreenShot(driver,className);*/
		
		

		
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.close();
	}

}
