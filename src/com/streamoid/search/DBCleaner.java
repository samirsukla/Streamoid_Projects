package com.streamoid.search;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;



import junit.framework.Assert;

public class DBCleaner {
	
	public WebDriver driver;
	String url = "http://tools.streamoid.com/dbcleaner2/DBCleaner.php";
	
	public void launchUrl() {
		
		System.setProperty("webdriver.chrome.driver", "/home/streamoid/Desktop/Samir_Streamoid/Selenium_Streamoid/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	public void loginAndSearch() {
		
		try {
			WebElement vendor_dropdown = driver.findElement(By.xpath("//label[text()='Vendor']/preceding-sibling::div/input"));
			vendor_dropdown.click();
			
			WebElement value_to_select = vendor_dropdown.findElement(By.xpath("//label[text()='Vendor']/preceding-sibling::div/ul/li[2]/span"));
			Actions act= new Actions(driver);
			act.moveToElement(value_to_select).click().build().perform();
			 Thread.sleep(3000);
			WebElement submit_button = driver.findElement(By.xpath("//div/a[text()='Submit']"));
			submit_button.click();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
				
		
	}
	
	public void selectCategory() {
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
		
		
		WebElement select_image = driver.findElement(By.xpath("//img[@title=\"ESSENTIAL - Shirt\"]"));
		act.moveToElement(select_image).doubleClick().build().perform();
	}
	
	public void saveChangesAndNext() {
		
			
				try {
					WebDriverWait wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/blockquote[@id='description']")));
					
					WebElement select_type = driver.findElement(By.xpath("//label[@id='label_bush']"));
					WebElement select_collar_type = driver.findElement(By.xpath("//label[@id='label_baseball']"));
					
					
                    if(!select_type.isSelected()) {
						
						select_type.click();
                    	
					}
                     
                    if(!select_collar_type.isSelected()) {
						
						select_collar_type.click();
                    	
					}
                    
                    JavascriptExecutor js = (JavascriptExecutor)driver;
                    js.executeScript("window.scrollBy(0,970)");
                    
                    WebElement select_sleeve_len = driver.findElement(By.xpath("//label[@id='label_below-elbow']"));
					WebElement select_sleeve_type = driver.findElement(By.xpath("//label[@for='sleeve_type_angel']"));
					WebElement select_shoulder_type = driver.findElement(By.xpath("//label[@for='shoulder_type_bare']"));
					
                    if(!select_sleeve_len.isSelected()) {
						
                    	select_sleeve_len.click();
                    	
					}
                     
                    if(!select_sleeve_type.isSelected()) {
						
                    	select_sleeve_type.click();
                    	
					}
                    if(!select_shoulder_type.isSelected()) {
						
                    	select_shoulder_type.click();
                    	
					}
                    
                    
                    js.executeScript("window.scrollBy(0,750)");
					
					WebElement select_hemline_len = driver.findElement(By.xpath("//label[@for='hemline_length_above-ankle']"));
					WebElement select_hemline_shape = driver.findElement(By.xpath("//input[@id='hemline_shape_diagonal']"));
					WebElement select_hemline_level = driver.findElement(By.xpath("//label[@for='hemline_level_even']"));
					WebElement select_silhouette = driver.findElement(By.xpath("//label[@for='silhouette_a-line']"));
					
					if(!select_hemline_len.isSelected()) {
						
						select_hemline_len.click();
                    	
					}
                     
                    if(!select_hemline_shape.isSelected()) {
		
                    	select_hemline_shape.click();
                    }
                    	
				   
                    	
                    
                    if(!select_hemline_level.isSelected()) {
						
                    	select_hemline_level.click();
                    	
					}
                    if(!select_silhouette.isSelected()) {
						
                    	select_silhouette.click();
                    	
					}
                    
                    
                    js.executeScript("window.scrollBy(0,850)");
					
					WebElement select_fit = driver.findElement(By.xpath("//label[@for='fit_fitted']"));
					WebElement select_print_type = driver.findElement(By.xpath("//label[@for='print_type_alligator']"));
					
                    if(!select_fit.isSelected()) {
						
                    	select_fit.click();
                    	
					}
                    if(!select_print_type.isSelected()) {
						
                    	select_print_type.click();
                    	
					}
                    
                    
                    js.executeScript("window.scrollBy(0,850)");
                    
					WebElement select_detail = driver.findElement(By.xpath("//label[@id='label_belt_at_anywhere']"));
					 
					 if(!select_detail.isSelected()) {
						
						 select_detail.click();
                    	
					}
                    
                    
                    js.executeScript("window.scrollBy(0,2900)");
                    
                    
                    
					WebElement select_embellish = driver.findElement(By.xpath("//input[@id='embellishment_aari_at_anywhere']"));
					WebElement select_occasion = driver.findElement(By.xpath("//input[@id='occasion_day-casual']"));
					
					if(!select_embellish.isSelected()) {
						
                    	select_embellish.click();
                    	
					}
                    if(!select_occasion.isSelected()) {
						
                    	select_occasion.click();
                    	
					}
                    
                    
                    js.executeScript("window.scrollBy(0,830)");
                    
					WebElement select_trim	 = driver.findElement(By.xpath("//label[@id='label_bead']"));
					WebElement select_collar_depth	 = driver.findElement(By.xpath("//label[@for='collar_depth_deep']"));
					WebElement select_color_width	 = driver.findElement(By.xpath("//label[@for='collar_width_extra-wide']"));
					
					if(!select_trim.isSelected()) {
						
						select_trim.click();
                    	
					}
                    if(!select_collar_depth.isSelected()) {
						
                    	select_collar_depth.click();
                    	
					}
                    if(!select_color_width.isSelected()) {
						
                    	select_color_width.click();
                    	
					}
                    
                    
                    js.executeScript("window.scrollBy(0,830)");
                    
					WebElement select_print_orientation	 = driver.findElement(By.xpath("//label[@for='print_orientation_diagonal']"));
					WebElement select_print_setting	 = driver.findElement(By.xpath("//label[@id='label_all-over']"));
					WebElement select_print_spacing = driver.findElement(By.xpath("//label[@id='label_dense']"));
					WebElement select_hemline_width = driver.findElement(By.xpath("//label[@for='hemline_width_extra-wide']"));
					WebElement select_back_type	 = driver.findElement(By.xpath("//label[@id='label_asymmetrical']"));
					
					if(!select_print_orientation.isSelected()) {
						
						select_print_orientation.click();
                    	
					}
                    if(!select_print_setting.isSelected()) {
						
                    	select_print_setting.click();
                    	
					}
                    if(!select_print_spacing.isSelected()) {
						
                    	select_print_spacing.click();
                    	
					}
                    if(!select_hemline_width.isSelected()) {
						
                    	select_hemline_width.click();
                    	
					}
                    if(!select_back_type.isSelected()) {
						
                    	select_back_type.click();
                    	
					}
                    
                    js.executeScript("window.scrollBy(0,830)");
                    
					WebElement select_back_depth = driver.findElement(By.xpath("//label[@for='back_depth_deep']"));
					WebElement select_print_size = driver.findElement(By.xpath("//label[@for='print_size_large']"));
					
					
                    if(!select_back_depth.isSelected()) {
						
                    	select_back_depth.click();
                    	
					}
                    
                    if(!select_print_size.isSelected()) {
                    	select_print_size.click();
                    }
                    
					
                    
				  WebElement save_changes = driver.findElement(By.xpath("//a[@id='saveForImage']"));
					save_changes.click();
					
					String actual = driver.findElement(By.xpath("//div[@id='toast-container']/div")).getText();
					Assert.assertEquals("Saved!", actual);
					
					
					
					WebElement click_next = driver.findElement(By.xpath("//a[@id='nextImage']"));
					click_next.click();
					Thread.sleep(3000);
				} 
                    catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			
		
	}
	
	public void tearDown() {
		
		driver.close();
	}

	public static void main(String[] args) {
		
		DBCleaner dc = new DBCleaner();
		dc.launchUrl();
        dc.loginAndSearch();
        dc.selectCategory();
        dc.saveChangesAndNext();
        dc.tearDown();
        
	}
	
	

}
