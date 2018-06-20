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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.services.utility.CheckWidgetPresentStatus;
import com.services.utility.ClickonFirstProduct;
import com.services.utility.CreateFolder;
import com.services.utility.GUICheckForSimilarProducts;
import com.services.utility.InitialSetup;
import com.services.utility.RestAPICheckForSimilarProducts;
import com.services.utility.ZoomInAndZoomOut;
import com.services.utility.GetSystemDate;

public class TestPeterEnglandInfo {
	
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
	static ClickonFirstProduct clickfirst;
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
		setZoomLevel = new ZoomInAndZoomOut();
		GetSystemDate getDate = new GetSystemDate();
		CreateFolder createFolder = new CreateFolder();
		currentDate = getDate.getPresentDate();
		createFolder.createDateDirectory(currentDate);
	}
  @Test
  public void testPeterEngland() throws Exception {
	  
	    String url = initSet.getUrl("Peter_England");
		driver.get(url);
	  
	  
		if(driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/ul/li[2]/div/img")).isDisplayed())
		{

		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/ul/li[2]/div/div/a[2]")).click();

		}
		
		mainCategoryName = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a")).getText();
		
		
		List<WebElement> child_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div"));
		
		for(int x=1; x<=child_categ_div.size(); x++) {
			int j=1;
			WebElement main_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
			clickfirst.moveToElement_only(main_category, driver);
			Thread.sleep(2000);
			
			while(x==2) {
				try {
					
				
				WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
				WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/ul/li["+j+"]/a"));
				String prodCategoryName = clickfirst.getCategoryName(product_category);
				
				clickfirst.moveToElementandClick(main_category1, product_category, driver);
				
				Thread.sleep(3000);
				
				product_id = clickfirst.clickOnPeterEnglandProduct(driver);
				
				Thread.sleep(2000);
				
				setZoomLevel.zoomOut(driver);
				
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600)");
				
				Thread.sleep(2000);
				
				if(driver.findElement(By.cssSelector(".similar_product_view.recomdtn")).isDisplayed())
				{
					status = "passed";
					List<String> similar_product_id = checkProducts.similarProducts(product_id,"peter_england");
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
				
				setZoomLevel.zoomIn(driver);
				
				j++;


				driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/a/img")).click();
				
				WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
				clickfirst.moveToElement_only(main_category2, driver);;
				Thread.sleep(2000);
				
			}
				
				catch(Exception e) {
					
					j=1;
					x++;
					
					
				}
			}
			
			List<WebElement> sub_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div"));
			for(int y=1;y<=sub_categ_div.size();y++) {
				j=1;
				
				try {
					
					while(driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div["+y+"]/ul/li["+j+"]/a")).isDisplayed()) {
						
						WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
						WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div["+y+"]/ul/li["+j+"]/a"));
						String prodCategoryName = clickfirst.getCategoryName(product_category);
						
						clickfirst.moveToElementandClick(main_category1, product_category, driver);
						
						Thread.sleep(3000);
						
						product_id = clickfirst.clickOnPeterEnglandProduct(driver);
						
						Thread.sleep(2000);
						
						setZoomLevel.zoomOut(driver);
						
						((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600)");
						Thread.sleep(2000);
						if(driver.findElement(By.cssSelector(".similar_product_view.recomdtn")).isDisplayed())
						{
							status = "passed";
							List<String> similar_product_id = checkProducts.similarProducts(product_id,"peter_england");
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
						setZoomLevel.zoomIn(driver);
						
						j++;

						driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/a/img")).click();
						
						WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
						clickfirst.moveToElement_only(main_category2, driver);
						Thread.sleep(2000);
						
						}
						
					}
				catch(Exception e) {
					
					j++;
					//driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/a/img")).click();
					WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/a"));
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
