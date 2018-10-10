package com.services.test;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.services.utility.CheckWidgetPresentStatus;
import com.services.utility.CreateFolder;
import com.services.utility.GUICheckForSimilarProducts;
import com.services.utility.InitialSetup;
import com.services.utility.RestAPICheckForSimilarProducts;
import com.services.utility.ZoomInAndZoomOut;
import com.services.utility.ClickonFirstProduct;
import com.services.utility.GetSystemDate;

public class TestSimonCarterInfo {
	
	
	public WebDriver driver;
	static Properties props;
	static FileInputStream finput;
	static String className;
	static String folderName;
	static String mainCategoryName;
	static WebElement widgetElement;
	static String status;
	static String product_id;
	static WebElement similar_widget ;
	static String currentDate = "";
	static String prodCategoryName;
	
	static ClickonFirstProduct clickfirst;
	static CheckWidgetPresentStatus checkStatus;
	static InitialSetup initSet;
	static RestAPICheckForSimilarProducts checkProducts;
	static GUICheckForSimilarProducts checkGUI;
	static ZoomInAndZoomOut setZoomLevel;
	
	
	@BeforeClass
	public void setUp() throws IOException {
		
		initSet = new InitialSetup();
		driver = initSet.initialSetup();
		clickfirst = new ClickonFirstProduct();
		className = this.getClass().getSimpleName();
		folderName = className.replace("Test", " ").replace("Info", " ").trim();
		checkStatus = new CheckWidgetPresentStatus();
		checkProducts = new RestAPICheckForSimilarProducts();
		checkGUI = new GUICheckForSimilarProducts();
		GetSystemDate getDate = new GetSystemDate();
		CreateFolder createFolder = new CreateFolder();
		currentDate = getDate.getPresentDate();
		createFolder.createDateDirectory(currentDate);
		setZoomLevel = new ZoomInAndZoomOut();
		
	}
  @Test
  public void testSimonCarter() throws Exception {
	  
	    String url = initSet.getUrl("Simon_Carter");
		driver.get(url);
		Thread.sleep(2000);

	  
		for(int i=1;i<=3; i++) {
		
		mainCategoryName = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a")).getText();
		try {
		if(i==2 ||i==3) {
			WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
			Actions act = new Actions(driver);
			act.moveToElement(main_category1).click().perform();
			
			Thread.sleep(3000);
			
			product_id = clickfirst.clickOnSimonCarterProduct(driver);
			
			Thread.sleep(2000);
			
			setZoomLevel.zoomOutGlobal(driver);
			
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
			
			Thread.sleep(2000);
			
			int widgetCount = driver.findElements(By.className("simon_carter_container")).size();
			
			if(widgetCount > 0)
			{
				status = "passed";
				List<String> similar_product_id = checkProducts.similarProducts_abof(product_id,"simon_carter");
				boolean isDisplaying = checkGUI.getIdsFromGUIforSimonCarter(similar_product_id,driver);
				
				checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, "NA", status,
						product_id,isDisplaying);
			}
			else
			{
				status = "failed";
				boolean isDisplaying = false;
				
				checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, "NA", status,
						product_id,isDisplaying);
				
			}
			setZoomLevel.zoomIn(driver);

			driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[1]/div/div[2]/a/img")).click();
			
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			
			driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[1]/div/div[2]/a/img")).click();

			
		}
		
		if(i==1) {
			
		
		List<WebElement> sub_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div"));
		
		for(int x=1; x<=sub_categ_div.size(); x++) {
			
			WebElement main_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
			
			clickfirst.moveToElement_only(main_category, driver);
			Thread.sleep(1000);
			
			int j=1;
						
			try {
				while(driver.findElement(By.xpath("//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/ul/li["+j+"]/a")).isDisplayed()) {
					
					WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
					WebElement product_category = driver.findElement(By.xpath("//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/ul/li["+j+"]/a"));
					prodCategoryName = clickfirst.getCategoryName(product_category);
					
					clickfirst.moveToElementandClick(main_category1, product_category, driver);
					Thread.sleep(3000);
					
					product_id = clickfirst.clickOnSimonCarterProduct(driver);
					
					Thread.sleep(2000);
					
					setZoomLevel.zoomOutGlobal(driver);
					
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
					
					Thread.sleep(2000);
					int widgetCount = driver.findElements(By.className("simon_carter_container")).size();
					
					if(widgetCount > 0)
					{
						status = "passed";
						List<String> similar_product_id = checkProducts.similarProducts_abof(product_id,"simon_carter");
						boolean isDisplaying = checkGUI.getIdsFromGUIforSimonCarter(similar_product_id,driver);
						
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
					setZoomLevel.zoomIn(driver);
					
					j++;

					driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[1]/div/div[2]/a/img")).click();
					WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
					clickfirst.moveToElement_only(main_category2, driver);
					}
					
					
				}
				catch(Exception e) {
					e.printStackTrace();
					j++;
					driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[1]/div/div[2]/a/img")).click();
					WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
					Actions act = new Actions(driver);
					act.moveToElement(main_category2).build().perform();
					
				}
			
			
		}
		
		}
	  
		}
  }
  
  
 

@AfterClass
  public void tearDown() {
	  
	  driver.quit();
  }
}
