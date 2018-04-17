package com.testNG.Streamoid;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class SearchSelectAndSave {
	
	
	public WebDriver driver;
	String url = "http://tools.streamoid.com/dbcleaner2/DBCleaner.php";
	@BeforeClass
	public void launchUrl() {
		
		System.setProperty("webdriver.chrome.driver", "/home/streamoid/Desktop/Samir_Streamoid/Selenium_Streamoid/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	@Test(invocationCount=5, threadPoolSize=3)
	public void loginAndSearch() {
		driver.get(url);
		WebElement vendor_dropdown = driver.findElement(By.xpath("//label[text()='Vendor']/preceding-sibling::div/ul/li[2]/span"));
		
		WebElement submit_button = driver.findElement(By.xpath("//div/a[text()='Submit']"));
		submit_button.click();
		
		//Code From selectCategory
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Gender']")));
		
		WebElement select_gender = driver.findElement(By.xpath("//label[text()='Gender']/preceding-sibling::div/input"));
		select_gender.click();
		WebElement value_to_select = select_gender.findElement(By.xpath("//label[text()='Gender']/preceding-sibling::div/ul/li[2]/span"));
		Actions act= new Actions(driver);
		act.moveToElement(value_to_select).click().build().perform();
		
		WebElement sel_categ = driver.findElement(By.xpath("//label[text()='Category']/preceding-sibling::div/input"));
		sel_categ.click();
		WebElement value_categ = sel_categ.findElement(By.xpath("//label[text()='Category']/preceding-sibling::div/ul/li[12]/span"));
		act.moveToElement(value_categ).click().build().perform();
		
		
		WebElement select_image = driver.findElement(By.xpath("//img[@title=\"ONYLINKA  - Cardigan\"]"));
		act.moveToElement(select_image).doubleClick().build().perform();
		
		//Code from saveChangesAndNext
		
		try {
			//WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/blockquote[@id='description']")));
			
			WebElement select_type = driver.findElement(By.xpath("//label[@id='label_award']/a"));
			WebElement select_hemline = driver.findElement(By.xpath("//label[@for='hemline_length_above-ankle']/a"));
			WebElement select_sleeve_len = driver.findElement(By.xpath("//a[@title='Below-elbow']"));
			
            if(!select_type.isSelected()) {
				
				select_type.click();
            	
			}
            
            if(!select_hemline.isSelected()) {
            	select_hemline.click();
            }
            
            if(!select_sleeve_len.isSelected()) {
            	select_sleeve_len.click();
            }
			
			
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,930)");
            
            
			WebElement select_sleeve_type = driver.findElement(By.xpath("//a[@title='Angel']"));
			WebElement select_shoulder = driver.findElement(By.xpath("//a[@title='Bare']"));
			WebElement select_fit = driver.findElement(By.xpath("//label[@for='fit_fitted']/a"));
			WebElement select_print	 = driver.findElement(By.xpath("//label[@for='print_type_alligator']/a"));
			
            if(!select_sleeve_type.isSelected()) {
				
				select_sleeve_type.click();
            	
			}
            
            if(!select_shoulder.isSelected()) {
            	select_shoulder.click();
            }
            
            if(!select_fit.isSelected()) {
            	select_fit.click();
            }
            
            if(!select_print.isSelected()) {
            	select_print.click();
            }
			
            
		    WebElement save_changes = driver.findElement(By.xpath("//a[@id='saveForImage']"));
			save_changes.click();
			
			String actual = driver.findElement(By.xpath("//div[@id='toast-container']/div")).getText();
			Assert.assertEquals("Saved!", actual);
			
			
			
			WebElement click_next = driver.findElement(By.xpath("//a[@id='nextImage']"));
			click_next.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 
				
		
	}
	/*@Test(priority=2)
	public void selectCategory() {
		
	}
	@Test(priority=3)
	public void saveChangesAndNext() {
		
			
				
				
			
		
	}*/
	@AfterClass
	public void tearDown() {
		
		driver.close();
	}
  
}
