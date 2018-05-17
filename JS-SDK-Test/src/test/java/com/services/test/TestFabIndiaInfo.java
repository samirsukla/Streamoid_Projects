package com.services.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.services.utility.CheckWidgetPresentStatus;
import com.services.utility.CreateFolder;
import com.services.utility.GUICheckForSimilarProducts;
import com.services.utility.InitialSetup;
import com.services.utility.ManageWindowHandle;
import com.services.utility.RestAPICheckForOutfitter;
import com.services.utility.clickonFirstProduct;
import com.services.utility.getSystemDate;

public class TestFabIndiaInfo {
	
	public WebDriver driver;
	static Properties props;
	static FileInputStream finput;
	static String className;
	static String folderName;
	static String mainCategoryName;
	static WebElement widgetElement;
	static String status;
	static WebElement outfitter_widget ;
	static String product_id;
	static String prodCategoryName;
	static String currentDate = "";
	
	static CheckWidgetPresentStatus checkStatus;
	static InitialSetup initSet;
	static clickonFirstProduct clickfirst;
	static RestAPICheckForOutfitter checkProducts;
	static GUICheckForSimilarProducts checkGUI;
	static ManageWindowHandle handle;
	
	
	@BeforeClass
	public void setUp() throws IOException {
		
		initSet = new InitialSetup();
		driver = initSet.initialSetup();
		clickfirst = new clickonFirstProduct();
		className = this.getClass().getSimpleName();
		folderName = className.replace("Test", " ").replace("Info", " ").trim();
		checkStatus = new CheckWidgetPresentStatus();
		checkProducts = new RestAPICheckForOutfitter();
		checkGUI = new GUICheckForSimilarProducts();
		handle = new ManageWindowHandle();
		getSystemDate getDate = new getSystemDate();
		CreateFolder createFolder = new CreateFolder();
		currentDate = getDate.getPresentDate();
		createFolder.createDateDirectory(currentDate);
		
	}
  @Test
  public void testFabIndia() throws Exception {
	  
	    String url = initSet.getUrl("Fab_India");
		driver.get(url);
		
		mainCategoryName = driver.findElement(By.xpath(".//*[@id='sample-menu-2']/li[2]/a/span[2]")).getText();
		
		WebElement main_category = driver.findElement(By.xpath(".//*[@id='sample-menu-2']/li[2]/a/span[2]"));
		clickfirst.moveToElement_only(main_category, driver);
		Thread.sleep(2000);
		
		int j=1;
		
		
		
		while(driver.findElement(By.xpath(".//*[@id='sub_floor_1']/div/div/div/div/div/div/div[1]/div[1]/dl/dd/ol/li["+j+"]/a")).isDisplayed()) {
			
			try {
			WebElement product_category1 = driver.findElement(By.xpath(".//*[@id='sub_floor_1']/div/div/div/div/div/div/div[1]/div[1]/dl/dd/ol/li["+j+"]/a"));
			prodCategoryName = clickfirst.getCategoryName(product_category1);
			product_category1.click();
			
			
			Thread.sleep(2000);
			
			if(j==2) {
				
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='Productshowcase']/div[1]/div/div[2]/div/a[1]/img")));
				
				WebElement first_element_link = driver.findElement(By.xpath("/html/body/form/div[3]/center/div/div/center/div[1]/div[3]/div[3]/div/div/div/div[3]/div[1]/div[1]/div/div[2]"));
				product_id = first_element_link.getAttribute("data-productid");
				
				handle.switchToNewWindow(driver);
				
				Thread.sleep(3000);
				
				if(driver.findElement(By.xpath(".//*[@id='stremoidDiv']/div")).isDisplayed()) {
					status="passed";
					List<String> outfitter_product_id = checkProducts.outfitterProducts(product_id,"fabindia_india");
					boolean isDisplaying = checkGUI.getIdsFromGUIforFabIndia(outfitter_product_id,driver);
					
					
					checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, prodCategoryName, status,
							product_id,isDisplaying);
					
				}
				else {
					status="failed";
					boolean isDisplaying = false;
					checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, prodCategoryName, status,
							product_id,isDisplaying);
					
				}
				
				handle.switchToParentWindow(driver);
				
				j+=3;
				driver.findElement(By.xpath("/html/body/form/div[3]/center/div/div/center/div[1]/div[1]/div[3]/div/div/div[1]/a/img")).click();
				WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='sample-menu-2']/li[2]/a/span[2]"));
				clickfirst.moveToElement_only(main_category2, driver);
				
				WebElement product_category2 = driver.findElement(By.xpath(".//*[@id='sub_floor_1']/div/div/div/div/div/div/div[1]/div[1]/dl/dd/ol/li["+j+"]/a"));
				prodCategoryName = clickfirst.getCategoryName(product_category2);
				product_category2.click();
				
				
			}
			
			List<WebElement> sub_menu = driver.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ctl00_Pane2']/div[2]/div[2]/div/ul/li[2]/ul/li[2]/ul/li[2]/ul/li"));
			for(int i=1;i<=sub_menu.size();i++) {
				
				driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ctl00_Pane2']/div[2]/div[2]/div/ul/li[2]/ul/li[2]/ul/li[2]/ul/li["+i+"]/a")).click();
			
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='Productshowcase']/div[1]/div/div[2]/div/a[1]/img")));
				
				WebElement first_element_link = driver.findElement(By.xpath("/html/body/form/div[3]/center/div/div/center/div[1]/div[3]/div[3]/div/div/div/div[3]/div[1]/div[1]/div/div[2]"));
				product_id = first_element_link.getAttribute("data-productid");
				
				handle.switchToNewWindow(driver);
				
				Thread.sleep(3000);
				
				if(driver.findElement(By.xpath(".//*[@id='stremoidDiv']/div")).isDisplayed()) {
					status="passed";
					List<String> outfitter_product_id = checkProducts.outfitterProducts(product_id,"fabindia_india");
					boolean isDisplaying = checkGUI.getIdsFromGUIforFabIndia(outfitter_product_id,driver);
					
					
					checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, prodCategoryName, status,
							product_id,isDisplaying);
					
				}
				else {
					status="failed";
					boolean isDisplaying = false;
					checkStatus.checkStatusAndTakeScreenshot(driver,currentDate, folderName, mainCategoryName, prodCategoryName, status,
							product_id,isDisplaying);
					
				}
				
				handle.switchToParentWindow(driver);
				
				
			}
			
			
			if(j==10) {
				break;
			}
			
			if(j%2==0) {
				
				j+=3;
			}
			else {
			j++;
			}
			
			driver.findElement(By.xpath("/html/body/form/div[3]/center/div/div/center/div[1]/div[1]/div[3]/div/div/div[1]/a/img")).click();
			WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='sample-menu-2']/li[2]/a/span[2]"));
			clickfirst.moveToElement_only(main_category2, driver);
		}
		catch(Exception e) {
			e.printStackTrace();
			
			driver.findElement(By.xpath("/html/body/form/div[3]/center/div/div/center/div[1]/div[1]/div[3]/div/div/div[1]/a/img")).click();
			WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='sample-menu-2']/li[2]/a/span[2]"));
			clickfirst.moveToElement_only(main_category2, driver);
		}
		
		}
		
		
	  
	  
		
	  
  }
  
  
  @AfterClass
  public void tearDown() {
	  
	  driver.close();
  }
}
