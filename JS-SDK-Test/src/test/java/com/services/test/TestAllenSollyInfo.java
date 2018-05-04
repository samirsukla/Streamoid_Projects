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
	static String className;
	static String folderName;
	static String mainCategoryName;
	static int z=1;
	
	@BeforeClass
	public void setUp() throws IOException {
		System.setProperty("webdriver.gecko.driver", "/home/streamoid/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		props = new Properties();
		finput = new FileInputStream("src/test/resources/URLInfo.properties");
		props.load(finput);
		clickfirst = new clickonFirstProduct();
		className = this.getClass().getSimpleName();
		folderName = className.replace("Test", " ").replace("Info", " ").trim();
		
		
		
		
	}
	@Test
	public void testAllenSolly() throws IOException, InterruptedException {
		
		String url = props.getProperty("Allen_Solly");
		driver.get(url);
		
		if(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[2]/div[2]/div[2]/div/img")).isDisplayed())
		{

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[2]/div[2]/div[2]/div/div/a[2]")).click();

		}
		
//		List<WebElement> main_category_list = driver.findElements(By.xpath("//ul[@id='nav-bar']/li"));
//		int main_category_size = main_category_list.size();
		
		for(int i=4;i<=6; i++) {
		
		mainCategoryName = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a")).getText();
		
		int j=1;
		List<WebElement> child_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div"));
		
		for(int x=1; x<=child_categ_div.size(); x++) {
			WebElement main_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
			clickfirst.moveToElement_only(main_category, driver);
			
			while(i==2 && x==1) {
				try {
				
				WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
				WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[2]/div/div/div[1]/ul/li["+j+"]/a"));
				String prodCategoryName = clickfirst.getCategoryName(product_category);
				
				clickfirst.moveToElementandClick(main_category1, product_category, driver);
                
				clickfirst.clickOnProduct(driver);
				
				/*List<WebElement> similar_product_widget = driver.findElements(By.xpath("/html/body/section/div[2]/div/div"));
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.visibilityOfAllElements(similar_product_widget));*/
				
				Thread.sleep(3000);
				
				scrshot = new takeScreenShot();
				scrshot.captureScreenShot(driver,folderName,mainCategoryName,prodCategoryName);

				j++;

				driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[2]/div[1]/a/img")).click();
				WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
				clickfirst.moveToElement_only(main_category2, driver);
				
			}
				
				catch(Exception e) {
					
					j=1;
					x++;
				}
			}
			
			while(i==5) {
				try {
					
				String prodCategoryName = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[5]/div/div/div["+z+"]/div/a/div/span")).getText();
				driver.findElement(By.xpath(".//*[@id='nav-bar']/li[5]/div/div/div["+z+"]/div/a/div")).click();
				
				clickfirst.clickOnProduct(driver);
				
				/*List<WebElement> similar_product_widget = driver.findElements(By.xpath("/html/body/section/div[2]/div/div"));
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.visibilityOfAllElements(similar_product_widget));*/
				
				Thread.sleep(3000);
				
				scrshot = new takeScreenShot();
				scrshot.captureScreenShot(driver,folderName,mainCategoryName,prodCategoryName);
				

				driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[2]/div[1]/a/img")).click();

//				WebElement main_category1= driver.findElement(By.xpath(".//*[@id='nav-bar']/li[5]/a"));
//				clickfirst.moveToElement_only(main_category1, driver);
				driver.findElement(By.xpath(".//*[@id='nav-bar']/li[5]/a")).click();
				z++;
				}
				catch(Exception e){
					
					i++;
					driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a")).click();
				}
			}
			
			List<WebElement> sub_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div["+x+"]/div"));
			
			for(int y=1;y<=sub_categ_div.size(); y++) {
				
				
				
				
				
				try {
				if(driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div["+x+"]/div["+y+"]/div/img")).isDisplayed()) {
					
					y++;
					mainCategoryName = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a")).getText();
				}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				
				try {
					while(driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div["+x+"]/div["+y+"]/ul/li["+j+"]/a")).isDisplayed()) {
						
						WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
						WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div["+x+"]/div["+y+"]/ul/li["+j+"]/a"));
						String prodCategoryName = clickfirst.getCategoryName(product_category);
						
						clickfirst.moveToElementandClick(main_category1, product_category, driver);
						
	                    
						clickfirst.clickOnProduct(driver);
						
						/*List<WebElement> similar_product_widget = driver.findElements(By.xpath("/html/body/section/div[2]/div/div"));
						WebDriverWait wait = new WebDriverWait(driver,10);
						wait.until(ExpectedConditions.visibilityOfAllElements(similar_product_widget));*/
						
						Thread.sleep(3000);
						
						scrshot = new takeScreenShot();
						scrshot.captureScreenShot(driver,folderName,mainCategoryName,prodCategoryName);
		
						j++;
	
						driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div[2]/div[1]/a/img")).click();
						WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
						clickfirst.moveToElement_only(main_category2, driver);
						
					}
						
					}
				catch(Exception e) {
					
					j=1;
					
				}
					
			}
			
		}
		
		}
	
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.close();
	}

}
