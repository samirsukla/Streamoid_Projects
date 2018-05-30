package com.services.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
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
import com.services.utility.CreateFolder;
import com.services.utility.GUICheckForSimilarProducts;
import com.services.utility.InitialSetup;
import com.services.utility.RestAPICheckForSimilarProducts;
import com.services.utility.clickonFirstProduct;
import com.services.utility.getSystemDate;

public class TestRaymondNextInfo {
	
	public WebDriver driver;
	static Properties props;
	static FileInputStream finput;
	static String className;
	static String folderName;
	static String mainCategoryName;
	static WebElement widgetElement;
	static String status;
	static WebElement similar_widget ;
	static String product_id;
	static String currentDate = "";
	
	
	static CheckWidgetPresentStatus checkStatus;
	static InitialSetup initSet;
	static clickonFirstProduct clickfirst;
	static RestAPICheckForSimilarProducts checkProducts;
	static GUICheckForSimilarProducts checkGUI;
	
	
	@BeforeMethod
	@BeforeClass
	public void setUp() throws IOException {
		
		initSet = new InitialSetup();
		driver = initSet.initialSetup();
		clickfirst = new clickonFirstProduct();
		className = this.getClass().getSimpleName();
		folderName = className.replace("Test", " ").replace("Info", " ").trim();
		checkStatus = new CheckWidgetPresentStatus();
		checkProducts = new RestAPICheckForSimilarProducts();
		checkGUI = new GUICheckForSimilarProducts();
		getSystemDate getDate = new getSystemDate();
		CreateFolder createFolder = new CreateFolder();
		currentDate = getDate.getPresentDate();
		createFolder.createDateDirectory(currentDate);
	}
  @Test
  public void testRaymondNext() throws Exception {
	  
	    String url = initSet.getUrl("Raymond_Next");
		driver.get(url);
	  
	  
		for(int i=1;i<=3;i++) {
		
		mainCategoryName = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li["+i+"]/a")).getText();
		
		int j=1;
		while(i==1) {
			
			List<WebElement> child_categ_div = driver.findElements(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li[1]/div/div/div/div"));
			for(int x=1; x<=child_categ_div.size(); x++) {
				WebElement main_category = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li["+i+"]/a"));
				clickfirst.moveToElement_only(main_category, driver);
				Thread.sleep(2000);
				
				try {
					while(driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li[1]/div/div/div/div["+x+"]/ul/li["+j+"]/a/span")).isDisplayed()) {
						
						WebElement main_category1 = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li[1]/a"));
						WebElement product_category = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li[1]/div/div/div/div["+x+"]/ul/li["+j+"]/a/span"));
						String prodCategoryName = clickfirst.getCategoryName(product_category);
						
						clickfirst.moveToElementandClick(main_category1, product_category, driver);
						Thread.sleep(3000);
						if(driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[2]/div[1]/div/div/div[1]/div[1]/a/img")).isDisplayed())
						{
							
							WebElement first_element_link = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[2]/div[2]/div[1]/div"));
							product_id = first_element_link.getAttribute("id").replaceAll("product-", "");
							
							clickfirst.clickOnRaymondProduct_1(driver);
							
							Thread.sleep(3000);
							
							WebElement similar_widget = driver.findElement(By.xpath("//div[@data-service='similar']"));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", similar_widget);
							if(driver.findElement(By.xpath("//div[@data-service='similar']")).isDisplayed())
							{
								status = "passed";
								List<String> similar_product_id = checkProducts.similarProducts(product_id,"raymond");
								boolean isDisplaying = checkGUI.getIdsFromGUIforPeterEngland(similar_product_id,driver);
								
								checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, prodCategoryName, status,
										product_id,isDisplaying);
							}
							else
							{
								status = "failed";
								boolean isDisplaying = false;
								
								checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, prodCategoryName, status,
										product_id,isDisplaying);
								
							}
							j++;

							driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/h1/a/img[1]")).click();
							
							WebElement main_category2 = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li["+i+"]/a"));
							clickfirst.moveToElement_only(main_category2, driver);
							Thread.sleep(2000);
							
						}
						else if( driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/div[2]/div[1]/div/div/div[1]/div[1]/a/img")).isDisplayed()) 
						{
						
							WebElement first_element_link = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/div[2]/div[1]/div"));
							product_id = first_element_link.getAttribute("id").replaceAll("product-", "");
							
							clickfirst.clickOnRaymondProduct_2(driver);
							
							Thread.sleep(3000);
							
							WebElement similar_widget = driver.findElement(By.xpath("//div[@data-service='similar']"));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", similar_widget);
							if(driver.findElement(By.xpath("//div[@data-service='similar']")).isDisplayed())
							{
								status = "passed";
								List<String> similar_product_id = checkProducts.similarProducts(product_id,"raymond");
								boolean isDisplaying = checkGUI.getIdsFromGUIforPeterEngland(similar_product_id,driver);
								
								checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, prodCategoryName, status,
										product_id,isDisplaying);
							}
							else
							{
								status = "failed";
								boolean isDisplaying = false;
								
								checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, prodCategoryName, status,
										product_id,isDisplaying);
								
							}
							j++;

							driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/h1/a/img[1]")).click();
							
							WebElement main_category2 = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li["+i+"]/a"));
							clickfirst.moveToElement_only(main_category2, driver);
							Thread.sleep(2000);
						}
						
						else {
						j++;

						driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/h1/a/img[1]")).click();
						
						WebElement main_category2 = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li["+i+"]/a"));
						clickfirst.moveToElement_only(main_category2, driver);
						Thread.sleep(2000);
						}
						
						}
						
					}
				catch(Exception e) {
					
					j=1;
					//driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/h1/a/img[1]")).click();
					WebElement main_category2 = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[3]/div/div[1]/ul/li["+i+"]/a"));
					clickfirst.moveToElement_only(main_category2, driver);
					
				}
				 x++;
			}
			
		}
	 
  }
  }
  
  
  @AfterMethod
@AfterClass
  public void tearDown() {
	  
	  driver.close();
  }
}
