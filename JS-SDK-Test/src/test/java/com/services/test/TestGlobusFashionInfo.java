package com.services.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.services.utility.CheckWidgetPresentStatus;
import com.services.utility.InitialSetup;
import com.services.utility.clickonFirstProduct;

public class TestGlobusFashionInfo {
	
	public WebDriver driver;
	static Properties props;
	static FileInputStream finput;
	static String className;
	static String folderName;
	static clickonFirstProduct clickfirst;
	static String mainCategoryName;
	static WebElement widgetElement;
	static CheckWidgetPresentStatus checkStatus;
	static InitialSetup initSet;
	
	
	@BeforeClass
	public void setUp() throws IOException {
		
		initSet = new InitialSetup();
		driver = initSet.initialSetup();
		clickfirst = new clickonFirstProduct();
		className = this.getClass().getSimpleName();
		folderName = className.replace("Test", " ").replace("Info", " ").trim();
		checkStatus = new CheckWidgetPresentStatus();
		
	}
  @Test
  public void testGlobusFashion() throws Exception {
	  
	    String url = initSet.getUrl("Globus_Fashion");
		driver.get(url);
	  
	  
		for(int i=1;i<=4; i++) {
		
		mainCategoryName = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/a/span")).getText();
		
		int j=1;
		List<WebElement> sub_categ_div = driver.findElements(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/div/div[1]/ul/li"));
		
		for(int y=1; y<=sub_categ_div.size(); y++) {
			WebElement main_category = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/a/span"));
			clickfirst.moveToElement_only(main_category, driver);
			Thread.sleep(2000);
			try {
				while(driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/div/div[1]/ul/li["+y+"]/div/div/ul/li["+j+"]/a/span")).isDisplayed()) {
					
					WebElement main_category1 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/a/span"));
					WebElement product_category1 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/div/div[1]/ul/li["+y+"]/div/div/ul/li["+j+"]/a/span"));
					String prodCategoryName = clickfirst.getCategoryName(product_category1);
					
					clickfirst.moveToElementandClick(main_category1, product_category1, driver);
					
//                    WebDriverWait wait = new WebDriverWait(driver, 5);
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li[1]/div/div[1]/ul/li[1]/div/div/ul/li[1]/a/span")));
				    clickfirst.clickOnGlobusProduct(driver);
					
				
					
				    Thread.sleep(2000);
					
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)"); 
					widgetElement = driver.findElement(By.className("globus_container"));
					checkStatus.checkStatusAndTakeScreenshot(driver, folderName, mainCategoryName, prodCategoryName, widgetElement);
					
					


					j++;

					driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div[2]/div/div[3]/div/h1/a/img")).click();
					WebElement main_category2 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/ul/li["+i+"]/a/span"));
					clickfirst.moveToElement_only(main_category2, driver);
					
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