package com.services.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class clickonFirstProduct {
	static Actions action;
	
	
	public void clickOnABOFProduct(WebDriver driver) throws InterruptedException {
		
		String product ="/html/body/div[3]/div[2]/div[2]/div/div[6]/div/div/div[1]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img";
		driver.findElement(By.xpath(product)).click();
		
	}
	
	public void clickOnGlobusProduct(WebDriver driver) throws InterruptedException {
		
		String product ="/html/body/div[1]/div/div/div[3]/div/div[2]/div[6]/div[5]/ul/li[1]/a/img";
		driver.findElement(By.xpath(product)).click();
		
	}
	
	
	public void clickOnLouisPhilippeProduct(WebDriver driver) throws InterruptedException {
		
		String product ="/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div[1]/div/div[1]";
		driver.findElement(By.xpath(product)).click();
		
		
	}
	
	public void clickOnFabIndiaProduct(WebDriver driver) throws InterruptedException {
		
		String product ="/html/body/form/div[3]/center/div/div/center/div[1]/div[3]/div[3]/div/div/div/div[3]/div[1]/div[1]/div/div[2]/div/a[1]/img";
		driver.findElement(By.xpath(product)).click();
		
		
	}
	
	public void clickOnPeterEnglandProduct(WebDriver driver) throws InterruptedException {
		
		String product ="/html/body/div[3]/div[4]/div[1]/div[1]/div/div[1]/div/ul/li[2]/a/div/img";
		driver.findElement(By.xpath(product)).click();
	}
	
	public void clickOnPeopleProduct(WebDriver driver) throws InterruptedException {
		
		String product ="/html/body/div[3]/div[1]/div[2]/div/div[6]/div/div/div[1]/div/div[1]/div[1]/div/div/ul/li[2]/a/div/img";
		driver.findElement(By.xpath(product)).click();
		
	}
	
public void clickOnRaymondProduct_1(WebDriver driver) throws InterruptedException {
		
		String product ="/html/body/div[1]/main/div[2]/div/div[2]/div[2]/div[1]/div/div/div[1]/div[1]/a/div";
		driver.findElement(By.xpath(product)).click();
		
	}

public void clickOnRaymondProduct_2(WebDriver driver) throws InterruptedException {
	
	String product ="/html/body/div[1]/main/div/div/div[3]/div[2]/div[1]/div/div/div[1]/div[1]/a/div";
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
