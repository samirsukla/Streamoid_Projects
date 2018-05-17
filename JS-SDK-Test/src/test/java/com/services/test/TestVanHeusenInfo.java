package com.services.test;

import org.testng.annotations.Test;

import com.services.utility.CheckWidgetPresentStatus;
import com.services.utility.CreateFolder;
import com.services.utility.GUICheckForSimilarProducts;
import com.services.utility.InitialSetup;
import com.services.utility.RestAPICheckForSimilarProducts;
import com.services.utility.clickonFirstProduct;
import com.services.utility.getSystemDate;
import com.services.utility.takeScreenShot;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestVanHeusenInfo {
	
	public WebDriver driver;
	static String className;
	static String folderName;
	static String mainCategoryName;
	static String status;
	static WebElement similar_widget ;
	static String product_id;
	static int z=1;
	static String currentDate = "";
	
	static clickonFirstProduct clickfirst;
	static takeScreenShot scrshot;
	static InitialSetup initSet;
	static CheckWidgetPresentStatus checkStatus;
	static RestAPICheckForSimilarProducts checkProducts;
	static GUICheckForSimilarProducts checkGUI;
	
	
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
	public void testVanHeusen() throws IOException, InterruptedException {
		
		String url = initSet.getUrl("Van_Heusen");
		driver.get(url);
		
		if(driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]//div/img")).isDisplayed())
		{

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]//div/div/a[2]")).click();

		}
		

		for(int i=1;i<=4; i++) {
		
		mainCategoryName = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a")).getText();
		
		int j=1;
		List<WebElement> child_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div"));
		
		for(int x=1; x<=child_categ_div.size(); x++) {
			
			WebElement main_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
			clickfirst.moveToElement_only(main_category, driver);
			Thread.sleep(2000);
			
			try
			{
			while(i==1 && x!=3 ) {
				
				
				List<WebElement> sub_categ_div = driver.findElements(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div["+x+"]/div"));
				
				for(int y=1;y<=sub_categ_div.size();y++) {
				
					List<WebElement> sub_division = driver.findElements(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div["+y+"]/ul"));
					
					for(int z=1;z<=sub_division.size();z++) {
						
					try {
						while(driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div["+y+"]/ul["+z+"]/li["+j+"]/a")).isDisplayed()) 
						{
						
						WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
						WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[1]/div/div/div["+x+"]/div["+y+"]/ul["+z+"]/li["+j+"]/a"));
						String prodCategoryName = clickfirst.getCategoryName(product_category);
				
						clickfirst.moveToElementandClick(main_category1, product_category, driver);
						
						clickfirst.clickOnABOFProduct(driver);
						
						Thread.sleep(3000);
						
						if(driver.findElement(By.className("vanheusen_vertical_container")).isDisplayed())
						{
							status = "passed";
							List<String> similar_product_id = checkProducts.similarProducts(product_id,"vanheusen");
							boolean isDisplaying = checkGUI.getIdsFromGUIforVanHeusen(similar_product_id,driver);
							
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

						driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/a/img")).click();
						WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
						clickfirst.moveToElement_only(main_category2, driver);
						}
					}
					catch(NoSuchElementException e) {
						j=1;
						//driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/a/img")).click();
						WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
						clickfirst.moveToElement_only(main_category2, driver);
					}
						
					}
				}
				x++;
				
					}
		}catch(Exception e) {
			i++;
			mainCategoryName = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a")).getText();
		}
			if(i==4) {
				try {
				while(driver.findElement(By.xpath(".//*[@id='nav-bar']/li[4]/div/div/div[2]/ul/li["+j+"]/a")).isDisplayed()) {
					
					WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
					WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li[4]/div/div/div[2]/ul/li["+j+"]/a"));
					String prodCategoryName = clickfirst.getCategoryName(product_category);
					
					clickfirst.moveToElementandClick(main_category1, product_category, driver);
					
		
					WebDriverWait wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div[1]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img")));
							
					WebElement first_element_link = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div[1]/div"));
					product_id = first_element_link.getAttribute("id").replaceAll("product_wrap_", "");
		
					clickfirst.clickOnABOFProduct(driver);
					
					Thread.sleep(3000);
					
					if(driver.findElement(By.className("vanheusen_vertical_container")).isDisplayed())
					{
						status = "passed";
						List<String> similar_product_id = checkProducts.similarProducts(product_id,"vanheusen");
						boolean isDisplaying = checkGUI.getIdsFromGUIforVanHeusen(similar_product_id,driver);
						
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

					driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/a/img")).click();
					WebElement main_category2 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
					clickfirst.moveToElement_only(main_category2, driver);
					
				}
				}
				catch(Exception e) {
					
					break;
					
					
				}
				
			}
			
			List<WebElement> sub_division = driver.findElements(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div["+x+"]/ul"));
			for (int z=1;z<=sub_division.size();z++) {
				
				try {
					while(driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div["+x+"]/ul["+z+"]/li["+j+"]/a")).isDisplayed()) {
						
						WebElement main_category1 = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/a"));
						WebElement product_category = driver.findElement(By.xpath(".//*[@id='nav-bar']/li["+i+"]/div/div/div["+x+"]/ul["+z+"]/li["+j+"]/a"));
						String prodCategoryName = clickfirst.getCategoryName(product_category);
						
						clickfirst.moveToElementandClick(main_category1, product_category, driver);
						
						WebDriverWait wait = new WebDriverWait(driver,10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div[1]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img")));
								
						WebElement first_element_link = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div[1]/div"));
						product_id = first_element_link.getAttribute("id").replaceAll("product_wrap_", "");
			
						clickfirst.clickOnABOFProduct(driver);
						
						Thread.sleep(3000);
						
						if(driver.findElement(By.className("vanheusen_vertical_container")).isDisplayed())
						{
							status = "passed";
							List<String> similar_product_id = checkProducts.similarProducts(product_id,"vanheusen");
							boolean isDisplaying = checkGUI.getIdsFromGUIforVanHeusen(similar_product_id,driver);
							
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
	
						driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/a/img")).click();
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
