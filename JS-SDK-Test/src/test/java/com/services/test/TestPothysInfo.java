package com.services.test;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.services.utility.CheckWidgetPresentStatus;
import com.services.utility.ClickonFirstProduct;
import com.services.utility.CreateFolder;
import com.services.utility.InitialSetup;
import com.services.utility.TakeScreenShot;
import com.services.utility.ZoomInAndZoomOut;
import com.services.utility.GetSystemDate;



public class TestPothysInfo {
	
	public WebDriver driver;
	static String className;
	static String folderName;
	static String mainCategoryName;
	static String status;
	static WebElement similar_widget ;
	static String currentDate ="";
	static String product_id = "NA";
	
	
	static ClickonFirstProduct clickfirst;
	static TakeScreenShot scrshot;
	static InitialSetup initSet;
	static CheckWidgetPresentStatus checkStatus;
	static ZoomInAndZoomOut setZoomLevel;
	
	
	

	@BeforeClass
	public void setUp() throws IOException {
		initSet = new InitialSetup();
		driver = initSet.initialSetup();
		clickfirst = new ClickonFirstProduct();
		className = this.getClass().getSimpleName();
		folderName = className.replace("Test", " ").replace("Info", " ").trim();
		checkStatus = new CheckWidgetPresentStatus();
		GetSystemDate getDate = new GetSystemDate();
		CreateFolder createFolder = new CreateFolder();
		currentDate = getDate.getPresentDate();
		createFolder.createDateDirectory(currentDate);
		setZoomLevel = new ZoomInAndZoomOut();
		
		
		}
	
	
	
	
  @Test
  public void testPothys() throws InterruptedException {
	  
	  String url = initSet.getUrl("Pothys");
	  driver.get(url);
	  
	  for(int i=1;i<=6; i++) {
			
			mainCategoryName = driver.findElement(By.xpath(".//*[@id='mainmenutop']/div/div/div/ul/li["+i+"]/a/span")).getText();
			
			
			List<WebElement> child_categ_div = driver.findElements(By.xpath(".//*[@id='mainmenutop']/div/div/div/ul/li["+i+"]/div/div/div/div"));
			
			for(int x=1; x<=child_categ_div.size(); x++) {
				int j=1;
				
				WebElement main_category = driver.findElement(By.xpath(".//*[@id='mainmenutop']/div/div/div/ul/li["+i+"]/a/span"));
				
				clickfirst.moveToElement_only(main_category, driver);
				Thread.sleep(2000);
				
				try {
					
					while(driver.findElement(By.xpath("/html/body/section/section[1]/section[2]/div/div/nav/div/div/div/ul/li["+i+"]/div/div/div/div["+x+"]/div/div/div[2]/div/ul/li["+j+"]/a")).isDisplayed()) {
					
						WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='mainmenutop']/div/div/div/ul/li["+i+"]/a/span"));
						WebElement product_category = driver.findElement(By.xpath("/html/body/section/section[1]/section[2]/div/div/nav/div/div/div/ul/li["+i+"]/div/div/div/div["+x+"]/div/div/div[2]/div/ul/li["+j+"]/a"));
						String prodCategoryName = clickfirst.getCategoryName(product_category).replaceAll("/", "-");
						
						clickfirst.moveToElementandClick(main_category1, product_category, driver);
						
						Thread.sleep(2000);
					
						
						clickfirst.clickOnPothysProduct(driver);
						
						Thread.sleep(4000);
						
//						setZoomLevel.zoomOutPothys(driver);
						
						WebElement similar_widget = driver.findElement(By.xpath("//div[@data-service='similar']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", similar_widget);
						Thread.sleep(2000);
						
						if(driver.findElement(By.xpath("//div[@data-service='similar']")).isDisplayed()) 
						{
							status="passed";
							boolean isDisplaying = true;
						
						
							checkStatus.checkStatusAndTakeScreenshot(driver, currentDate,folderName, mainCategoryName, prodCategoryName, status,product_id,isDisplaying);
						}
						else
						{
							status="failed";
							boolean isDisplaying = false;
							checkStatus.checkStatusAndTakeScreenshot(driver, currentDate,folderName, mainCategoryName, prodCategoryName, status,product_id,isDisplaying);
							
						}
						
//						setZoomLevel.zoomIn(driver);
						((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)"); 
		
						j++;
	
						driver.findElement(By.xpath("/html/body/section/section[1]/section[1]/div[2]/div/div[1]/div/a/img")).click();
						WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='mainmenutop']/div/div/div/ul/li["+i+"]/a/span"));
						clickfirst.moveToElement_only(main_category2, driver);
						
					}
						
					}
				catch(Exception e) {
					j++;
					WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='mainmenutop']/div/div/div/ul/li["+i+"]/a/span"));
					clickfirst.moveToElement_only(main_category2, driver);
					
				}
			}
	  }
		
  }
 

@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
}
