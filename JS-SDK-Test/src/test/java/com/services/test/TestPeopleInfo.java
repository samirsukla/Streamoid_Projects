package com.services.test;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.services.utility.CheckWidgetPresentStatus;
import com.services.utility.CreateFolder;
import com.services.utility.GUICheckForSimilarProducts;
import com.services.utility.InitialSetup;
import com.services.utility.RestAPICheckForSimilarProducts;
import com.services.utility.ClickonFirstProduct;
import com.services.utility.GetSystemDate;

public class TestPeopleInfo {
	
	
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
	static String currentDate= "";
	
	static ClickonFirstProduct clickfirst;
	static CheckWidgetPresentStatus checkStatus;
	static InitialSetup initSet;
	static RestAPICheckForSimilarProducts checkProducts;
	static GUICheckForSimilarProducts checkGUI;
	
	
	
	
	
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
		
	}
  @Test
  public void testPeople() throws Exception {
	  
	    String url = initSet.getUrl("People");
		driver.get(url);
	  
		for(int i=2;i<=3; i++) {
		
		mainCategoryName = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a")).getText();
		
		
		List<WebElement> sub_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div/div"));
		
		for(int x=1; x<=sub_categ_div.size(); x++) {
			int j=1;
			WebElement main_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
			clickfirst.moveToElement_only(main_category, driver);
			Thread.sleep(2000);
			
			try {
				while(driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div/div["+x+"]/ul/li["+j+"]/a")).isDisplayed()) {
					
					WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
					WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div/div["+x+"]/ul/li["+j+"]/a"));
					String prodCategoryName = clickfirst.getCategoryName(product_category);
					
					clickfirst.moveToElementandClick(main_category1, product_category, driver);
					Thread.sleep(3000);
					
					product_id = clickfirst.clickOnPeople_Planet_LouisProduct(driver);
					
					Thread.sleep(2000);
					
					int widgetCount = driver.findElements(By.className("people_vertical_container")).size();
					
					if(widgetCount > 0)
					{
						status = "passed";
						List<String> similar_product_id = checkProducts.similarProducts(product_id,"people");
						boolean isDisplaying = checkGUI.getIdsFromGUIforPeople(similar_product_id,driver);
						
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

					driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/div/div[1]/div/a/img")).click();
					WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
					clickfirst.moveToElement_only(main_category2, driver);
					
					}
					
				}
			catch(Exception e) {
				
				j++;
				
				WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
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
