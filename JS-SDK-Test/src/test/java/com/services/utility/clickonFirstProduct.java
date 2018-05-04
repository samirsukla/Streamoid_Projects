package com.services.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class clickonFirstProduct {
	static Actions action;
	
	
	public void clickOnProduct(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		String product ="/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div[1]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img";
		driver.findElement(By.xpath(product)).click();
		
	}
	
	public void clickOnGlobusProduct(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		String product ="/html/body/div[1]/div/div/div[3]/div/div[2]/div[6]/div[5]/ul/li[1]/a/img";
		driver.findElement(By.xpath(product)).click();
		
	}
	
	public void moveToElementandClick(WebElement main_category , WebElement product_category , WebDriver driver) {
		action = new Actions(driver);
		action.moveToElement(main_category).moveToElement(product_category).click().build().perform();
	}
	
	public void moveToElement_only(WebElement product_Category , WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		action = new Actions(driver);
		action.moveToElement(product_Category).build().perform();

	}
	
	public String getCategoryName(WebElement element) {
		return  element.getText();
		
	}

}
